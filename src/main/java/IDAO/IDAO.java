package IDAO;

import java.util.ArrayList;

public interface IDAO <T> {

    Boolean insert(T joureur);
    T getById(T id);
    ArrayList<T> getAll();
}
