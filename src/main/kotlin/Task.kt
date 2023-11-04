class Task(name: String) {

    private var state: State = State.NONE

    public fun setState(state: State) {
        this.state = state
    }
}