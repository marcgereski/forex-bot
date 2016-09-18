package kz.kase.bot.storage.predicate;

import kz.kase.bot.model.domain.InstrHolder;

public class InstrByExtCode implements Predicate<InstrHolder> {

    private final String instrExtCode;

    public InstrByExtCode(String instrExtCode) {
        this.instrExtCode = instrExtCode;
    }

    @Override
    public boolean matches(InstrHolder instrHolder) {
        return instrHolder.getNin().equals(instrExtCode);
    }
}