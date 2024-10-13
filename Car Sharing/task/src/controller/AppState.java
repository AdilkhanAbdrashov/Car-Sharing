package controller;

@FunctionalInterface
public interface AppState {

    AppState exec();
}
