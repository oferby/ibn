package nlu;

import com.huawei.ibn.nlu.model.UserInputEntity;
import com.huawei.ibn.nlu.model.UserInputRepository;

import java.util.Optional;

public class MockUserInputRepository implements UserInputRepository {
    @Override
    public <S extends UserInputEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserInputEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserInputEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserInputEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<UserInputEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserInputEntity userInputEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserInputEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
