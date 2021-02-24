package com.lxc.recruitment.component.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.lxc.base.http.entity.UserEntity
import com.lxc.recruitment.R
import com.lxc.recruitment.component.admin.AdminHomeFragment
import com.lxc.recruitment.component.admin.AdminMyFragment
import com.lxc.recruitment.component.company.CompanyHomeFragment
import com.lxc.recruitment.component.company.CompanyMyFragment
import com.lxc.recruitment.component.personal.PersonalHomeFragment
import com.lxc.recruitment.component.personal.PersonalMyFragment
import com.lxc.recruitment.constants.Constants
import com.lxc.recruitment.viewmodels.AdminViewModel
import com.lxc.recruitment.viewmodels.CompanyViewModel
import com.lxc.recruitment.viewmodels.PersonalViewModel
import com.lxc.recruitment.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var adapter: FragmentAdapter
    private val personalViewModel: PersonalViewModel by viewModels()
    private val adminViewModel: AdminViewModel by viewModels()
    private val companyViewModel: CompanyViewModel by viewModels()

    private val fragments: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = Constants.USER
        when (user.type) {
            UserEntity.ADMIN -> {
                adminViewModel.queryAdminInfo()
                fragments.add(AdminHomeFragment())
                fragments.add(MessageFragment())
                fragments.add(AdminMyFragment())
            }
            UserEntity.PERSONAL -> {
                // 查询个人信息
                personalViewModel.queryPersonalInfo()
                fragments.add(PersonalHomeFragment())
                fragments.add(MessageFragment())
                fragments.add(PersonalMyFragment())
            }
            UserEntity.COMPANY -> {
                // 查询个人信息
                companyViewModel.queryCompanyInfo()
                fragments.add(CompanyHomeFragment())
                fragments.add(MessageFragment())
                fragments.add(CompanyMyFragment())
            }
        }
        adapter = FragmentAdapter(
            fragments,
            requireActivity().supportFragmentManager
        )

        activity?.let { activity ->
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = fragments[0]
            fragmentTransaction.add(R.id.vp_content, fragment)
            fragmentTransaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bottomNavigationView.inflateMenu(R.menu.menu_personal)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_person_main -> {
                    personalViewModel.pos = 0
                }
                R.id.menu_person_message -> {
                    personalViewModel.pos = 1
                }
                R.id.menu_person_my -> {
                    personalViewModel.pos = 2
                }
            }
            replaceFragment(personalViewModel.pos)
            true
        }
        replaceFragment(personalViewModel.pos)
    }

    private fun replaceFragment(pos: Int,isRemove: Boolean = false) {
        activity?.let {
            val fragmentManager: FragmentManager = it.supportFragmentManager
            personalViewModel.pos = pos
            fragmentManager.beginTransaction()
                .replace(R.id.vp_content, fragments[pos])
                .commit()
        }
    }


    class FragmentAdapter(private val mFragments: List<Fragment>, fm: FragmentManager?) :
        FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(i: Int): Fragment {
            return mFragments[i]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

    }
}

