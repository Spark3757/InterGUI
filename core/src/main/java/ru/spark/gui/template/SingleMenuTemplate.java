package ru.spark.gui.template;

import ru.spark.gui.session.MenuSession;
import ru.spark.gui.window.MenuWindow;

import java.util.*;

public class SingleMenuTemplate extends AbstractMenuTemplate {

    private final MenuWindow rootWindow;

    private final Map<String, MenuWindow> rootWindowAsMap;

    public SingleMenuTemplate(MenuWindow rootWindow) {
        this.rootWindowAsMap = Collections.singletonMap(
                MAIN_WINDOW_ID,
                this.rootWindow = rootWindow
        );
    }

    @Override
    public MenuWindow getRootWindow() {
        return this.rootWindow;
    }

    @Override
    public Map<String, MenuWindow> getAllWindows() {
        return this.rootWindowAsMap;
    }

    @Override
    public Optional<MenuWindow> findWindow(String id) {
        return id.equalsIgnoreCase(MAIN_WINDOW_ID)
                ? Optional.of(this.rootWindow)
                : Optional.empty();
    }

    @Override
    public Map<String, MenuWindow> getWindowsPerSession(MenuSession session) {
        return this.canView(session)
                ? this.rootWindowAsMap
                : Collections.emptyMap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SingleMenuTemplate that = (SingleMenuTemplate) o;

        return Objects.equals(this.rootWindow, that.rootWindow) &&
                Objects.equals(this.rootWindowAsMap, that.rootWindowAsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.rootWindow,
                this.rootWindowAsMap
        );
    }
}
