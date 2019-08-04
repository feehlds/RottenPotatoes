package Repositorios;

public interface Repository<T> {
	public void add(T item);
	public void remove(T item);
}
