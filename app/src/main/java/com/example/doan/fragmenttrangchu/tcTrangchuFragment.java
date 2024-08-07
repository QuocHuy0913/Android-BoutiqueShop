package com.example.doan.fragmenttrangchu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.example.doan.DetailSPActivity;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.Glide;
import com.example.doan.Sanpham;
import com.example.doan.adapter.sanphamAdapter;
import com.example.doan.my_interface.IClickItemSPListener;
import com.example.doan.ui.Utils;
import com.google.gson.Gson;
import com.example.doan.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tcTrangchuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tcTrangchuFragment extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView rcvSanpham;
    sanphamAdapter mSanphamAdapter;
    View mView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tcTrangchuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tcTrangchuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tcTrangchuFragment newInstance(String param1, String param2) {
        tcTrangchuFragment fragment = new tcTrangchuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tc_trangchu, container, false);
        viewFlipper = mView.findViewById(R.id.viewflipper);
        ActionViewFlipper();

        rcvSanpham = mView.findViewById(R.id.rcv_sanpham);
        mSanphamAdapter = new sanphamAdapter(getListSP(), new IClickItemSPListener() {
            @Override
            public void onClickItemSP(Sanpham sanpham) {
                onClickGoToDetail(sanpham);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rcvSanpham.setLayoutManager(gridLayoutManager);
        mSanphamAdapter.setData(getListSP());
        rcvSanpham.setAdapter(mSanphamAdapter);
        return mView;
    }

    private ArrayList<Sanpham> getListSP() {
        ArrayList<Sanpham> list = new ArrayList<>();
        list.add(new Sanpham(21, "sp7.jpg", "Cropped Sleeveless Bra Top", 29.90, "- Mattle texture is great for sports or casual wear\n- Stylish color block lines at the sides\n- Active racer-back design with mesh on the inside\n- Under band provides gentle support", "Nu", 0));
        list.add(new Sanpham(18, "sp4.jpg", "AIRism Cotton Square Neck Sleeveless Bra Top", 29.90, "- Smooth AIRism fabric with quick-drying DRY technology\n- With moisture-wicking, odor control, Cool Touch, and deodorization features\n- Numerous added features keep you dry and comfortable\n- Excellent stretch for a smooth feel and superb comfort\n- Hem design with no under-bust band at the back", "Nu", 0));
        list.add(new Sanpham(25, "sp11.jpg", "Premium Linen Stand Collar Striped Long-Sleeve Shirt", 39.90, "- Cool 100% high quality material made of 100% linen cultivated in Europe\n- Hight-quality long fiber linen for a fine lustrous sheen\n- The more you wear it, the softer it becomes\n- Flattering stand collar design", "Nam", 0));
        list.add(new Sanpham(27, "sp13.jpg", "Linen Cotton Stand Collar Short-Sleeve Shirt", 39.90, "- Specially-treated fabric with an updated texture and reduced wrinkling\n- Roomy regular fit allows easy movement\n- Stand collar for a sleek-looking neckline", "Nam", 0));
        list.add(new Sanpham(34, "sp20.jpg", "Ultra Stretch Dry Sweat Long-Sleeve", 29.90, "- Outer newly update with 100% cotton for less pilling\n- Smooth polyester lining\n- With DRY technology\n- The perfect balance of comfort and style\n- Can be worn all year round", "Treem", 0));
        list.add(new Sanpham(35, "sp21.jpg", "Ultra Stretch Dry Sweat Long-Sleeve Full-Zip-Hoodie", 29.90, "- Ultra stretch fabric with all-direction stretch for total comfort\n- With quick-drying DRY technology\n- Chin guard protects the face form the zipper", "Treem", 0));
        list.add(new Sanpham(45, "sp32.jpg", "Jeans Baby Bibs", 14.90, "- Design for sensitive baby skin using flat seams for minimal skin contact\n- Hypoallergenic plastic snap-fasteners\n- Soft 100% cotton ribbed knit fabric", "Tresosinh", 0));
        list.add(new Sanpham(43, "sp30.jpg", "Baby Long-Sleeve Sweater", 12.90, "- Padding, lining made from recycled material\n- Machine washable\n- Care label inside the garment on the left side\n- Flat elastic at the cuffs and hem to keep cold air out", "Tresosinh", 0));
        list.add(new Sanpham(49, "sp36.jpg", "Ribbled Short-Sleeve Outfit", 10.90, "- Made with soft 100% cotton ribbed knit fabric\n- Stretchy fabric for freedom of movement and easy outfit changes", "Tresosinh", 0));
        return list;
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/resdf976ee47455d33332484a9cb14779a5fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res79a6fbe3669ac7b24a06e6942096a0ecfr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res7c177efa5488812b920bce2542b85847fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res0c4ebd709e6c55fc4336dc1e0a2ff613fr.jpg");
        for(int i = 0; i < mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getContext().getApplicationContext());
            Glide.with(getContext().getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
    private void onClickGoToDetail(Sanpham sanpham)
    {
        Intent i = new Intent(getContext(), DetailSPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_SP", sanpham);
        i.putExtras(bundle);
        startActivity(i);
    }

}