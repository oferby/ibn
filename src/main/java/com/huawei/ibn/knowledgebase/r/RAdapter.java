package com.huawei.ibn.knowledgebase.r;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

//import org.python.core.PyList;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(RAdapter.class);

    private final Rengine engine;
    private final boolean consoleActive;


    private static Map<String, String> packagesToLoad = new LinkedHashMap<String, String>();

    static {
		packagesToLoad.put("avivTest", "inHouseInfoBayes_0.1.0.tar.gz");
    }

    public RAdapter(final String rFiles[], final boolean consoleActive)
            throws IOException {
        this.consoleActive = consoleActive;

        if (!Rengine.versionCheck()) {
            throw new RuntimeException("Mismatch R version");
        }
        this.engine = new Rengine(new String[]{"--vanilla"}, false,
                new NullLoopCallbacks());
        //this.engine.setDaemon(true);
        if (!this.engine.waitForR()) {
            throw new RuntimeException("R cannot be loaded");
        }
        for (final String file : rFiles) {
            this.engine.eval("source(\"" + file + "\")");
        }

        loadLocalPackagesToR();
    }

    private void loadLocalPackagesToR() {

        try {
            this.lock();

            Iterator<Entry<String, String>> it = packagesToLoad.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> pair = it.next();
                this.engine.eval("install.packages(\"" + (System.getProperty("user.dir")).replaceAll("\\\\", "/") + "/src/main/resources/" + pair.getValue() + "\", repos = NULL, type = \"source\")");
                this.engine.eval("library(" + pair.getKey() + ")");
                it.remove(); // avoids a ConcurrentModificationException
            }

            //this.engine.eval("library(forecast)");
            //this.engine.eval("detach(\"package:forecast\", unload=TRUE)");

        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        } finally {
            this.release();
        }
    }

    public RAdapter(final String rFiles[]) throws IOException {
        this(rFiles, false);
    }

    public RAdapter() throws IOException {
        this(new String[0], false);
    }

    public RAdapter(final boolean consoleActive) throws IOException {
        this(new String[0], consoleActive);
    }

    public RAdapter(final String rFile) throws IOException {
        this(new String[]{rFile}, false);
    }

    public RAdapter(final String rFile, final boolean consoleActive)
            throws IOException {
        this(new String[]{rFile}, consoleActive);
    }

    /**
     * Evals R file.
     */
    public void evalFile(final String rFile) throws IOException {
        this.engine.eval("source(\"" + rFile + "\")");
    }

    /**
     * Finishes R execution.
     */
    @SuppressWarnings("deprecation")
    public void end() {
        if (!this.consoleActive)
            this.engine.stop();
        else
            this.engine.startMainLoop();
    }

    /**
     * * Lets the name of the variable containing given value. * According to
     * object type, different kind of assignations will be made.
     */
    public void set(final String name, final Object value) {
        if (value instanceof boolean[]) {
            this.engine.assign(name, (boolean[]) value);
        } else if (value instanceof double[]) {
            this.engine.assign(name, (double[]) value);
        } else if (value instanceof int[]) {
            this.engine.assign(name, (int[]) value);
        } else if (value instanceof String) {
            final REXP x = this.engine.eval(value.toString());
            this.engine.assign(name, x);
        } else {
            LOG.error("Ignoring unknown type " + value.getClass()
                    + " to assign");
        }
    }

    /**
     * * Lets the name of the variable containing list of numbers. *
     * Specifically desinged to used from Python.
     */
//	public void setDoubles(final String name, final PyList list) {
//		final double[] values = new double[list.size()];
//		for (int i = 0; i < values.length; i++) {
//			values[i] = (Double) list.get(i);
//		}
//		this.engine.assign(name, values);
//	}

    /**
     * Evals R expression.
     */
    public REXP eval(final String what) {
        return this.engine.eval(what);
    }

    /**
     * Evals R expression as a boolean expression.
     */
    public boolean evalBool(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asBool().isTRUE();
    }

    /**
     * Evals R expression as a string expression.
     */
    public String evalString(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asString();
    }

    /**
     * Evals R expression as a string expression.
     */
    public String[] evalStringArray(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asStringArray();
    }

    /**
     * Evals R expression as a double expression.
     */
    public double evalDouble(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asDouble();
    }

    /**
     * Evals R expression as a list of double values.
     */
    public double[] evalDoubleArray(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asDoubleArray();
    }

    /**
     * Evals R expression as a matrix of double values.
     */
    public double[][] evalDoubleMatrix(final String what) {
        final REXP x = this.engine.eval(what);
        if (x == null)
            throw new REngineException(what);
        return x.asDoubleMatrix();
    }

    /**
     * * Locks access to R to other threads. * Lock must be released in order to
     * let other thread to execute * R functionallity.
     */
    public void lock() {
    }

    /**
     * Releases locked object for the current thread.
     */
    public void release() {
    }

    private final class NullLoopCallbacks implements RMainLoopCallbacks {
        public void rWriteConsole(Rengine re, String text, int oType) {
            if (consoleActive) {
                LOG.error(text);
            }
        }

        public void rBusy(Rengine re, int which) {
        }

        public String rReadConsole(final Rengine re, final String prompt,
                                   final int addToHistory) {
            if (consoleActive) {
                LOG.error(prompt);
                try {
                    final BufferedReader br = new BufferedReader(
                            new InputStreamReader(System.in));
                    final String s = br.readLine();
                    return (s == null || s.length() == 0) ? s : s + "\n";
                } catch (Exception e) {
                    LOG.error("jriReadConsole exception: "
                            + e.getMessage());
                }
            }
            return null;
        }

        public void rShowMessage(Rengine re, String message) {
            if (consoleActive) {
                LOG.debug("rShowMessage \"" + message + "\"");
            }
        }

        public String rChooseFile(Rengine re, int newFile) {
            /*             FileDialog fd = new FileDialog(new Frame(),             (newFile == 0) ? "Select a file" : "Select a new file",             (newFile == 0) ? FileDialog.LOAD : FileDialog.SAVE);             fd.setVisible(true);             String res = null;             if (fd.getDirectory() != null) res = fd.getDirectory();             if (fd.getFile() != null) {                 res = (res == null) ? fd.getFile() : (res + fd.getFile());             }             return res;         */
            return null;
        }

        public void rFlushConsole(final Rengine re) {
        }

        public void rLoadHistory(final Rengine re, final String filename) {
        }

        public void rSaveHistory(final Rengine re, final String filename) {
        }
    }

    public static String javaListToRlist(LinkedList<Double> values) {
        String javaListToRList = "c" + ((values.toString()).replaceAll("\\[", "\\(")).replaceAll("\\]", "\\)");
        return javaListToRList;
    }

}