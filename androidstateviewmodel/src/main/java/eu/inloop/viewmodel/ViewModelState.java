package eu.inloop.viewmodel;

/**
 * State tracking class for {@link AbstractStateViewModel}.
 * <p/>
 * Fragment or activity which is associated with viewmodel have to properly set the state of this
 * component, so the associated viewmodel can query it for relevant informations.
 *
 * @see AbstractStateViewModel
 */
public class ViewModelState {
    /**
     * Indication that viewmodel is removed.
     */
    private boolean removed;

    /**
     * Indication that viewmodel is active.
     */
    private boolean active;

    /**
     * Indication that viewmodel was coldstarted.
     */
    private boolean coldStart = true;

    /**
     * Indication that view was restarted because of configuration change.
     */
    private boolean configChange;

    /**
     * Sets the active state. This have to be set to <code>true</code> before {@link
     * AbstractStateViewModel#onStart()} in associated viewmodel is called and set to <code>false</code> before {@link
     * AbstractStateViewModel#onStop()} is called.
     *
     * @param active active state
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Checks if the viewmodel is active. If this method returns <code>true</code>, it is quaranteed
     * that view associated with the viewmodel is available.
     *
     * @return <code>true</code> if viewmodel is active, <code>false</code> otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the removed state. This have to be set to <code>true</code> before {@link
     * AbstractStateViewModel#onModelRemoved()} in associated viewmodel is called, and keep the value until the instance of
     * viewmodel is destroyed.
     */
    public void setRemoved() {
        this.removed = true;
    }

    /**
     * Checks if the viewmodel is removed.
     *
     * @return <code>true</code> if viewmodel is removed, <code>false</code> otherwise
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * Sets the coldstart state. Relevant value have to be set before {@link
     * AbstractStateViewModel#onStart()} in associated viewmodel is called and kept until the {@link
     * AbstractStateViewModel#onStart()} is called again.
     *
     * @param coldStart cold start state
     */
    public void setColdStart(boolean coldStart) {
        this.coldStart = coldStart;
    }

    /**
     * Checks if cold start occured.
     * @return <code>true</code> if cold start occured, <code>false</code> otherwise
     */
    public boolean isColdStart() {
        return coldStart;
    }

    /**
     * Sets the config change state. Relevant value have to be set before {@link
     * AbstractStateViewModel#onStop()} in associated viewmodel is called and kept until the {@link
     * AbstractStateViewModel#onStop()} is called again.
     *
     * @param configChange config change state
     */
    public void setConfigChange(boolean configChange) {
        this.configChange = configChange;
    }

    /**
     * Checks if config change occured.
     * @return <code>true</code> if config change occured, <code>false</code> otherwise
     */
    public boolean isConfigChange() {
        return configChange;
    }
}
