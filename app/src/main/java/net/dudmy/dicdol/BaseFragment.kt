package net.dudmy.dicdol

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by yujin on 2017. 4. 23..
 */

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(getLayoutId(), container, false)
}