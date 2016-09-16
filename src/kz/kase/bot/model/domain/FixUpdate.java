package kz.kase.bot.model.domain;


import quickfix.Message;

public interface FixUpdate<M extends Message, T> {

    T getObject();

    M getMessage();
}
