package com.tron.wallet.common.config;
public interface CommonBundleKeys {
    public static final String HAS_SHOW_THE_LEARN_MORE_POP = "has_show_the_learn_pop";
    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_ALL_MY_VOTE_RIGHTS = "key_all_my_vote_rights";
    public static final String KEY_ALREADY_VOTED_WITNESSES = "key_already_voted_witnesses";
    public static final String KEY_APY_TOP3_WITNESSES = "key_apy_top3_witnesses";
    public static final String KEY_CONTROLLER_ADDRESS = "key_controller_address";
    public static final String KEY_CONTROLLER_NAME = "key_controller_name";
    public static final String KEY_DAPP_URL_OUTSIDE = "key_dapp_url_outside";
    public static final String KEY_FILTER_MY_VOTE = "key_filter_my_vote";
    public static final String KEY_FROM = "key_from";
    public static final String KEY_FROM_MULTI_SIGN = "key_from_multi";
    public static final String KEY_IS_FROM_STAKE_SUCCESS = "key_is_from_stake_success";
    public static final String KEY_MY_AVAILABEL_VOTE = "key_my_availabel_vote";
    public static final String KEY_PERMISSION_DATA = "key_multi_sign_permission";
    public static final String KEY_SELECTED_WITNESSES = "key_selected_witnesses";
    public static final String KEY_SORT_INDEX = "key_sort_index";

    public interface StakeResult {
        public static final String KEY_FAILED_COUNT = "key_failed_count";
        public static final String KEY_MSG = "key_fail_msg";
        public static final String KEY_STATE = "key_state";
        public static final String KEY_TRANS_PARAM = "key_trans_param";
    }

    public interface UnStakeResult {
        public static final String KEY_FAILED_COUNT = "key_failed_count";
        public static final String KEY_STATE = "key_state";
        public static final String KEY_TRANS_PARAM = "key_trans_param";
    }

    public interface UpgradeHd {
        public static final String KEY_BEAN_PARAM = "key_bean_param";
    }
}
