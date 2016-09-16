package kz.kase.bot.model.domain;


import quickfix.Message;

public class FixUpdateImpl implements FixUpdate {

    private final Message mess;
    private final Object holder;


    public FixUpdateImpl(Message fixMess, Object holder) {
        this.mess = fixMess;
        this.holder = holder;
    }

    @Override
    public Object getObject() {
        return holder;
    }

    @Override
    public Message getMessage() {
        return mess;
    }

    public String getName() {
        return mess.getClass().getSimpleName();
    }
}
