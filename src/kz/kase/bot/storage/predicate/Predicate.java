package kz.kase.bot.storage.predicate;

public interface Predicate<T> {

    boolean matches(T t);
}
