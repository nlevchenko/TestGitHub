package levchenko.nikita.testgithub.core

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.subscribeOnIO() = subscribeOn(Schedulers.io())
fun <T> Observable<T>.observeOnMainThread() = observeOn(AndroidSchedulers.mainThread())