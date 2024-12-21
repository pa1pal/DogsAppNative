package `in`.pawan.dogsapp

class IntentHandledIdlingResource : androidx.test.espresso.IdlingResource {

    private var callback: androidx.test.espresso.IdlingResource.ResourceCallback? = null
    private var isIdle = false

    override fun getName(): String {
        return "IntentHandledIdlingResource"
    }

    override fun isIdleNow(): Boolean {
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: androidx.test.espresso.IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    // Call this method when the intent has been handled in your activity
    fun onIntentHandled() {
        isIdle = true
        callback?.onTransitionToIdle()
    }
}