package br.com.fleury.commons_android.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter


class TabFragmentPageAdapter(private val fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun addTabFragment(fragment: Fragment, title: String, position: Int? = null) {
        val pos = position ?: fragments.size
        if (pos >= fragments.size) {
            fragments.add(fragment)
            titles.add(title)
        } else {
            fragments.add(pos, fragment)
            titles.add(pos, title)
        }
        notifyDataSetChanged();
    }

    fun removeFragmentAtPosition(position: Int) {
        val f = fragments.removeAt(position)
        fm.beginTransaction().remove(f).commit()
        titles.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return System.identityHashCode(fragments[position]).toLong()
    }

    override fun getItemPosition(obj: Any): Int {
        val idx = fragments.indexOf(obj)
        return if (idx < 0) PagerAdapter.POSITION_NONE else idx
    }

}