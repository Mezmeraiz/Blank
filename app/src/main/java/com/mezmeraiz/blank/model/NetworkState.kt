package com.mezmeraiz.blank.model

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState (val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String? = "unknown error") =
            NetworkState(Status.FAILED, msg)
    }
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String? = "unknown error"): Resource<T> {
            return Resource(Status.FAILED, null, msg)
        }
    }
}
