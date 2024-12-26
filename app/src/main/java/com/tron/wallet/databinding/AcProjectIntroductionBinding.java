package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ItemWarningTagView;
import com.tronlinkpro.wallet.R;
public final class AcProjectIntroductionBinding implements ViewBinding {
    public final TextView accuracy;
    public final TextView contractAddress;
    public final TextView endTime;
    public final ItemTokenHeadBinding ilHeader;
    public final ImageView ivCopy;
    public final ImageView ivIdCopy;
    public final ImageView ivNoProxy;
    public final ImageView ivOpenInWebviewArraw;
    public final ImageView ivReportArraw;
    public final ImageView ivSecureBlackList;
    public final ImageView ivSecureCreateSelf;
    public final ImageView ivSecureHaveUrl;
    public final ImageView ivSecureOpenSource;
    public final ImageView ivUrlCopy;
    public final LinearLayout liSecureInfo;
    public final RelativeLayout llProjectBaseInfo;
    public final TextView projectName;
    public final RelativeLayout rlAccuracy;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlDescribe;
    public final RelativeLayout rlEndtime;
    public final RelativeLayout rlId;
    public final RelativeLayout rlNoProxy;
    public final RelativeLayout rlProjectName;
    public final RelativeLayout rlPublisher;
    public final ItemWarningTagView rlScam;
    public final RelativeLayout rlSecureBlackList;
    public final RelativeLayout rlSecureCreateSelf;
    public final RelativeLayout rlSecureHaveUrl;
    public final RelativeLayout rlSecureOpenSource;
    public final RelativeLayout rlStartTime;
    public final RelativeLayout rlTokenUrl;
    public final RelativeLayout rlTotalCirculation;
    private final RelativeLayout rootView;
    public final TextView startTime;
    public final TextView tokenDescribe;
    public final TextView tokenId;
    public final TextView tokenPublisher;
    public final TextView tokenUrl;
    public final TextView totalCirculation;
    public final TextView tvBlackList;
    public final TextView tvContractAddressLabel;
    public final TextView tvDescribeLabel;
    public final TextView tvEndTimeLabel;
    public final TextView tvHaveUrl;
    public final TextView tvIssueSelf;
    public final TextView tvNoProxy;
    public final TextView tvOpenInWebview;
    public final TextView tvOpenSource;
    public final TextView tvProjectName;
    public final TextView tvProjectReport;
    public final TextView tvPublisherLabel;
    public final TextView tvStartTime;
    public final TextView tvTokenId;
    public final TextView tvTokenSecureInfo;
    public final TextView tvTokenUrl;
    public final TextView tvTotalCirculationLabel;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcProjectIntroductionBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, ItemTokenHeadBinding itemTokenHeadBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView4, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, RelativeLayout relativeLayout7, RelativeLayout relativeLayout8, RelativeLayout relativeLayout9, RelativeLayout relativeLayout10, ItemWarningTagView itemWarningTagView, RelativeLayout relativeLayout11, RelativeLayout relativeLayout12, RelativeLayout relativeLayout13, RelativeLayout relativeLayout14, RelativeLayout relativeLayout15, RelativeLayout relativeLayout16, RelativeLayout relativeLayout17, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27) {
        this.rootView = relativeLayout;
        this.accuracy = textView;
        this.contractAddress = textView2;
        this.endTime = textView3;
        this.ilHeader = itemTokenHeadBinding;
        this.ivCopy = imageView;
        this.ivIdCopy = imageView2;
        this.ivNoProxy = imageView3;
        this.ivOpenInWebviewArraw = imageView4;
        this.ivReportArraw = imageView5;
        this.ivSecureBlackList = imageView6;
        this.ivSecureCreateSelf = imageView7;
        this.ivSecureHaveUrl = imageView8;
        this.ivSecureOpenSource = imageView9;
        this.ivUrlCopy = imageView10;
        this.liSecureInfo = linearLayout;
        this.llProjectBaseInfo = relativeLayout2;
        this.projectName = textView4;
        this.rlAccuracy = relativeLayout3;
        this.rlAddress = relativeLayout4;
        this.rlDescribe = relativeLayout5;
        this.rlEndtime = relativeLayout6;
        this.rlId = relativeLayout7;
        this.rlNoProxy = relativeLayout8;
        this.rlProjectName = relativeLayout9;
        this.rlPublisher = relativeLayout10;
        this.rlScam = itemWarningTagView;
        this.rlSecureBlackList = relativeLayout11;
        this.rlSecureCreateSelf = relativeLayout12;
        this.rlSecureHaveUrl = relativeLayout13;
        this.rlSecureOpenSource = relativeLayout14;
        this.rlStartTime = relativeLayout15;
        this.rlTokenUrl = relativeLayout16;
        this.rlTotalCirculation = relativeLayout17;
        this.startTime = textView5;
        this.tokenDescribe = textView6;
        this.tokenId = textView7;
        this.tokenPublisher = textView8;
        this.tokenUrl = textView9;
        this.totalCirculation = textView10;
        this.tvBlackList = textView11;
        this.tvContractAddressLabel = textView12;
        this.tvDescribeLabel = textView13;
        this.tvEndTimeLabel = textView14;
        this.tvHaveUrl = textView15;
        this.tvIssueSelf = textView16;
        this.tvNoProxy = textView17;
        this.tvOpenInWebview = textView18;
        this.tvOpenSource = textView19;
        this.tvProjectName = textView20;
        this.tvProjectReport = textView21;
        this.tvPublisherLabel = textView22;
        this.tvStartTime = textView23;
        this.tvTokenId = textView24;
        this.tvTokenSecureInfo = textView25;
        this.tvTokenUrl = textView26;
        this.tvTotalCirculationLabel = textView27;
    }

    public static AcProjectIntroductionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcProjectIntroductionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_project_introduction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcProjectIntroductionBinding bind(View view) {
        int i = R.id.accuracy;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.accuracy);
        if (textView != null) {
            i = R.id.contract_address;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.contract_address);
            if (textView2 != null) {
                i = R.id.end_time;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.end_time);
                if (textView3 != null) {
                    i = R.id.il_header;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.il_header);
                    if (findChildViewById != null) {
                        ItemTokenHeadBinding bind = ItemTokenHeadBinding.bind(findChildViewById);
                        i = R.id.iv_copy;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                        if (imageView != null) {
                            i = R.id.iv_id_copy;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_id_copy);
                            if (imageView2 != null) {
                                i = R.id.iv_no_proxy;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_no_proxy);
                                if (imageView3 != null) {
                                    i = R.id.iv_open_in_webview_arraw;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_open_in_webview_arraw);
                                    if (imageView4 != null) {
                                        i = R.id.iv_report_arraw;
                                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_report_arraw);
                                        if (imageView5 != null) {
                                            i = R.id.iv_secure_black_list;
                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_secure_black_list);
                                            if (imageView6 != null) {
                                                i = R.id.iv_secure_create_self;
                                                ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_secure_create_self);
                                                if (imageView7 != null) {
                                                    i = R.id.iv_secure_have_url;
                                                    ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_secure_have_url);
                                                    if (imageView8 != null) {
                                                        i = R.id.iv_secure_open_source;
                                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_secure_open_source);
                                                        if (imageView9 != null) {
                                                            i = R.id.iv_url_copy;
                                                            ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_url_copy);
                                                            if (imageView10 != null) {
                                                                i = R.id.li_secure_info;
                                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_secure_info);
                                                                if (linearLayout != null) {
                                                                    i = R.id.ll_project_base_info;
                                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_project_base_info);
                                                                    if (relativeLayout != null) {
                                                                        i = R.id.project_name;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.project_name);
                                                                        if (textView4 != null) {
                                                                            i = R.id.rl_accuracy;
                                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_accuracy);
                                                                            if (relativeLayout2 != null) {
                                                                                i = R.id.rl_address;
                                                                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                                                                if (relativeLayout3 != null) {
                                                                                    i = R.id.rl_describe;
                                                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_describe);
                                                                                    if (relativeLayout4 != null) {
                                                                                        i = R.id.rl_endtime;
                                                                                        RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_endtime);
                                                                                        if (relativeLayout5 != null) {
                                                                                            i = R.id.rl_id;
                                                                                            RelativeLayout relativeLayout6 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_id);
                                                                                            if (relativeLayout6 != null) {
                                                                                                i = R.id.rl_no_proxy;
                                                                                                RelativeLayout relativeLayout7 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_no_proxy);
                                                                                                if (relativeLayout7 != null) {
                                                                                                    i = R.id.rl_project_name;
                                                                                                    RelativeLayout relativeLayout8 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_project_name);
                                                                                                    if (relativeLayout8 != null) {
                                                                                                        i = R.id.rl_publisher;
                                                                                                        RelativeLayout relativeLayout9 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_publisher);
                                                                                                        if (relativeLayout9 != null) {
                                                                                                            i = R.id.rl_scam;
                                                                                                            ItemWarningTagView itemWarningTagView = (ItemWarningTagView) ViewBindings.findChildViewById(view, R.id.rl_scam);
                                                                                                            if (itemWarningTagView != null) {
                                                                                                                i = R.id.rl_secure_black_list;
                                                                                                                RelativeLayout relativeLayout10 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_secure_black_list);
                                                                                                                if (relativeLayout10 != null) {
                                                                                                                    i = R.id.rl_secure_create_self;
                                                                                                                    RelativeLayout relativeLayout11 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_secure_create_self);
                                                                                                                    if (relativeLayout11 != null) {
                                                                                                                        i = R.id.rl_secure_have_url;
                                                                                                                        RelativeLayout relativeLayout12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_secure_have_url);
                                                                                                                        if (relativeLayout12 != null) {
                                                                                                                            i = R.id.rl_secure_open_source;
                                                                                                                            RelativeLayout relativeLayout13 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_secure_open_source);
                                                                                                                            if (relativeLayout13 != null) {
                                                                                                                                i = R.id.rl_start_time;
                                                                                                                                RelativeLayout relativeLayout14 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_start_time);
                                                                                                                                if (relativeLayout14 != null) {
                                                                                                                                    i = R.id.rl_token_url;
                                                                                                                                    RelativeLayout relativeLayout15 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_token_url);
                                                                                                                                    if (relativeLayout15 != null) {
                                                                                                                                        i = R.id.rl_total_circulation;
                                                                                                                                        RelativeLayout relativeLayout16 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_total_circulation);
                                                                                                                                        if (relativeLayout16 != null) {
                                                                                                                                            i = R.id.start_time;
                                                                                                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.start_time);
                                                                                                                                            if (textView5 != null) {
                                                                                                                                                i = R.id.token_describe;
                                                                                                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.token_describe);
                                                                                                                                                if (textView6 != null) {
                                                                                                                                                    i = R.id.token_id;
                                                                                                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.token_id);
                                                                                                                                                    if (textView7 != null) {
                                                                                                                                                        i = R.id.token_publisher;
                                                                                                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.token_publisher);
                                                                                                                                                        if (textView8 != null) {
                                                                                                                                                            i = R.id.token_url;
                                                                                                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.token_url);
                                                                                                                                                            if (textView9 != null) {
                                                                                                                                                                i = R.id.total_circulation;
                                                                                                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.total_circulation);
                                                                                                                                                                if (textView10 != null) {
                                                                                                                                                                    i = R.id.tv_black_list;
                                                                                                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_black_list);
                                                                                                                                                                    if (textView11 != null) {
                                                                                                                                                                        i = R.id.tv_contract_address_label;
                                                                                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_address_label);
                                                                                                                                                                        if (textView12 != null) {
                                                                                                                                                                            i = R.id.tv_describe_label;
                                                                                                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_describe_label);
                                                                                                                                                                            if (textView13 != null) {
                                                                                                                                                                                i = R.id.tv_end_time_label;
                                                                                                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_end_time_label);
                                                                                                                                                                                if (textView14 != null) {
                                                                                                                                                                                    i = R.id.tv_have_url;
                                                                                                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_have_url);
                                                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                                                        i = R.id.tv_issue_self;
                                                                                                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_issue_self);
                                                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                                                            i = R.id.tv_no_proxy;
                                                                                                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_proxy);
                                                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                                                i = R.id.tv_open_in_webview;
                                                                                                                                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_open_in_webview);
                                                                                                                                                                                                if (textView18 != null) {
                                                                                                                                                                                                    i = R.id.tv_open_source;
                                                                                                                                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_open_source);
                                                                                                                                                                                                    if (textView19 != null) {
                                                                                                                                                                                                        i = R.id.tv_project_name;
                                                                                                                                                                                                        TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_project_name);
                                                                                                                                                                                                        if (textView20 != null) {
                                                                                                                                                                                                            i = R.id.tv_project_report;
                                                                                                                                                                                                            TextView textView21 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_project_report);
                                                                                                                                                                                                            if (textView21 != null) {
                                                                                                                                                                                                                i = R.id.tv_publisher_label;
                                                                                                                                                                                                                TextView textView22 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_publisher_label);
                                                                                                                                                                                                                if (textView22 != null) {
                                                                                                                                                                                                                    i = R.id.tv_start_time;
                                                                                                                                                                                                                    TextView textView23 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_start_time);
                                                                                                                                                                                                                    if (textView23 != null) {
                                                                                                                                                                                                                        i = R.id.tv_token_id;
                                                                                                                                                                                                                        TextView textView24 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_id);
                                                                                                                                                                                                                        if (textView24 != null) {
                                                                                                                                                                                                                            i = R.id.tv_token_secure_info;
                                                                                                                                                                                                                            TextView textView25 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_secure_info);
                                                                                                                                                                                                                            if (textView25 != null) {
                                                                                                                                                                                                                                i = R.id.tv_token_url;
                                                                                                                                                                                                                                TextView textView26 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_url);
                                                                                                                                                                                                                                if (textView26 != null) {
                                                                                                                                                                                                                                    i = R.id.tv_total_circulation_label;
                                                                                                                                                                                                                                    TextView textView27 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_circulation_label);
                                                                                                                                                                                                                                    if (textView27 != null) {
                                                                                                                                                                                                                                        return new AcProjectIntroductionBinding((RelativeLayout) view, textView, textView2, textView3, bind, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, linearLayout, relativeLayout, textView4, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, relativeLayout6, relativeLayout7, relativeLayout8, relativeLayout9, itemWarningTagView, relativeLayout10, relativeLayout11, relativeLayout12, relativeLayout13, relativeLayout14, relativeLayout15, relativeLayout16, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
