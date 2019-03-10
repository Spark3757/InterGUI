package ru.spark.gui.processor;

import ru.spark.gui.session.MenuSession;

public interface MenuOpenProcessor {

    OpenProcessorResponse open(MenuSession menuSession);

    OpenProcessorResponse update(MenuSession menuSession);

    OpenProcessorResponse updateItem(MenuSession menuSession, int index);

}
