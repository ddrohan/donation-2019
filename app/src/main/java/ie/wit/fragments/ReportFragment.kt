package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import ie.wit.R
import ie.wit.adapters.DonationAdapter
import ie.wit.main.DonationApp
import ie.wit.models.DonationModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_report.*
import kotlinx.android.synthetic.main.fragment_report.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportFragment : Fragment(), AnkoLogger, Callback<List<DonationModel>> {

    lateinit var app: DonationApp
    lateinit var loader : AlertDialog
    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DonationApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_report, container, false)
        activity?.title = getString(R.string.action_report)

        root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        root.recyclerView.adapter = DonationAdapter(app.donationsStore.findAll())
        loader = createLoader(activity!!)
        setSwipeRefresh()
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReportFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    fun setSwipeRefresh() {
        root.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                root.swiperefresh.isRefreshing = true
                getAllDonations()
            }
        })
    }

    fun checkSwipeRefresh() {
        if (root.swiperefresh.isRefreshing) root.swiperefresh.isRefreshing = false
    }

    override fun onFailure(call: Call<List<DonationModel>>, t: Throwable) {
        info("Retrofit Error : $t.message")
        serviceUnavailableMessage(activity!!)
        checkSwipeRefresh()
        hideLoader(loader)
    }

    override fun onResponse(call: Call<List<DonationModel>>,
                        response: Response<List<DonationModel>>
    ) {
        serviceAvailableMessage(activity!!)
        info("Retrofit JSON = ${response.body()}")
        app.donationsStore.donations = response.body() as ArrayList<DonationModel>
        root.recyclerView.adapter = DonationAdapter(app.donationsStore.findAll())
        root.recyclerView.adapter?.notifyDataSetChanged()
        checkSwipeRefresh()
        hideLoader(loader)
    }

    fun getAllDonations() {
        showLoader(loader, "Downloading the Donations List")
        var callGetAll = app.donationService.getall()
        callGetAll.enqueue(this)
    }

    override fun onResume() {
        super.onResume()
        getAllDonations()
    }
}
