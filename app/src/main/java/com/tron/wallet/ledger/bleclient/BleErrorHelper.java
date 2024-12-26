package com.tron.wallet.ledger.bleclient;

import android.content.Context;
import com.tron.wallet.ledger.bleclient.OnBleError;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import com.tronlinkpro.wallet.R;
import java.util.concurrent.TimeoutException;
public class BleErrorHelper {

    public interface OnConnectErrorCallback extends OnErrorCallback {
        void onDeviceNotFound(BleError bleError);
    }

    public interface OnGetAddressErrorCallback extends OnOpenTronAppErrorCallback {
        void onBIP32PathError(BleError bleError);
    }

    public interface OnOpenTronAppErrorCallback extends OnErrorCallback {
        void onTronAppNotInstalled(BleError bleError);

        void onTronAppNotOpen(BleError bleError);

        void onUserDenied(BleError bleError);
    }

    public interface OnSignErrorCallback extends OnOpenTronAppErrorCallback {
        void onTronAppSettingCustomContractNotAllowed(BleError bleError);

        void onTronAppSettingDataNotAllowed(BleError bleError);

        void onTronAppSettingSignHashNotAllowed(BleError bleError);
    }

    public static String getErrorString(Context context, BleError bleError) {
        String str = null;
        if (bleError.errorId == BleErrorId.BleErrorCode) {
            int intValue = bleError.statusCode.intValue();
            if (intValue != 21761) {
                if (intValue != 26368) {
                    if (intValue != 26628) {
                        if (intValue != 26631) {
                            if (intValue != 27010) {
                                if (intValue != 27012) {
                                    if (intValue != 27013) {
                                        switch (intValue) {
                                            case LedgerStatusCode.MISSING_SETTING_DATA_ALLOWED:
                                                str = context.getResources().getString(R.string.unapproved_on_ledger_extra_data);
                                                break;
                                            case LedgerStatusCode.MISSING_SETTING_SIGN_BY_HASH:
                                                str = context.getResources().getString(R.string.unapproved_on_ledger_sign_hash);
                                                break;
                                            case LedgerStatusCode.MISSING_SETTING_CUSTOM_CONTRACT:
                                                str = context.getResources().getString(R.string.unapproved_on_ledger_custom_contract);
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                        str = context.getResources().getString(R.string.tron_not_installed);
                    } else {
                        str = context.getResources().getString(R.string.ledger_locked);
                    }
                }
                str = context.getResources().getString(R.string.open_tron_app);
            }
            str = context.getResources().getString(R.string.declined_by_ledger);
        } else if (bleError.errorId == BleErrorId.DeviceDisconnected || bleError.errorId == BleErrorId.DeviceNotConnected) {
            str = context.getResources().getString(R.string.device_disconnected);
        } else if (bleError.errorId == BleErrorId.OperationTimedOut || (bleError.getCause() != null && (bleError.getCause() instanceof TimeoutException))) {
            str = context.getResources().getString(R.string.action_timeout);
        } else if (bleError.errorId == BleErrorId.DeviceNotFound) {
            str = context.getResources().getString(R.string.device_disconnected);
        } else if (bleError.errorId == BleErrorId.VarintIsTooBig || bleError.errorId == BleErrorId.DataLengthTooBig) {
            str = context.getResources().getString(R.string.transaction_field_is_too_long);
        }
        if (str == null) {
            String string = context.getResources().getString(R.string.action_failed);
            if (bleError.statusCode != null) {
                return string + String.format(context.getResources().getString(R.string.action_failed_code), bleError.statusCode);
            }
            return string;
        }
        return str;
    }

    public interface OnErrorCallback {
        void onConnectionDisconnected(BleError bleError);

        void onPreError(BleError bleError);

        void onTimeout(BleError bleError);

        void onUnKnowError(BleError bleError);

        public final class -CC {
        }
    }

    public static class OnErrorHandler implements OnBleError {
        protected OnErrorCallback onErrorCallback;

        @Override
        public void accept(Object obj) {
            accept((Throwable) obj);
        }

        @Override
        public void accept(Throwable th) {
            OnBleError.-CC.$default$accept((OnBleError) this, th);
        }

        public OnErrorHandler(OnErrorCallback onErrorCallback) {
            this.onErrorCallback = onErrorCallback;
        }

        @Override
        public void onError(BleError bleError) {
            this.onErrorCallback.onPreError(bleError);
            if (bleError.errorId == BleErrorId.DeviceDisconnected || bleError.errorId == BleErrorId.DeviceNotConnected) {
                this.onErrorCallback.onConnectionDisconnected(bleError);
            } else if (bleError.errorId == BleErrorId.OperationTimedOut || (bleError.getCause() != null && (bleError.getCause() instanceof TimeoutException))) {
                this.onErrorCallback.onTimeout(bleError);
            } else {
                this.onErrorCallback.onUnKnowError(bleError);
            }
        }
    }

    public static class OnConnectErrorHandler extends OnErrorHandler {
        public OnConnectErrorHandler(OnConnectErrorCallback onConnectErrorCallback) {
            super(onConnectErrorCallback);
        }

        @Override
        public void onError(BleError bleError) {
            OnConnectErrorCallback onConnectErrorCallback = (OnConnectErrorCallback) this.onErrorCallback;
            if (bleError.errorId == BleErrorId.DeviceNotFound) {
                onConnectErrorCallback.onDeviceNotFound(bleError);
            } else {
                super.onError(bleError);
            }
        }
    }

    public static class OnOpenTronAppErrorHandler extends OnErrorHandler {
        public OnOpenTronAppErrorHandler(OnOpenTronAppErrorCallback onOpenTronAppErrorCallback) {
            super(onOpenTronAppErrorCallback);
        }

        @Override
        public void onError(com.tron.wallet.ledger.blemodule.errors.BleError r4) {
            


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.ledger.bleclient.BleErrorHelper.OnOpenTronAppErrorHandler.onError(com.tron.wallet.ledger.blemodule.errors.BleError):void");
        }
    }

    public static class OnGetAddressErrorHandler extends OnOpenTronAppErrorHandler {
        public OnGetAddressErrorHandler(OnGetAddressErrorCallback onGetAddressErrorCallback) {
            super(onGetAddressErrorCallback);
        }

        @Override
        public void onError(BleError bleError) {
            OnGetAddressErrorCallback onGetAddressErrorCallback = (OnGetAddressErrorCallback) this.onErrorCallback;
            if (bleError.errorId == BleErrorId.BleErrorCode && bleError.statusCode.intValue() == 27274) {
                onGetAddressErrorCallback.onBIP32PathError(bleError);
            } else {
                super.onError(bleError);
            }
        }
    }

    public static class OnSignErrorHandler extends OnOpenTronAppErrorHandler {
        public OnSignErrorHandler(OnSignErrorCallback onSignErrorCallback) {
            super(onSignErrorCallback);
        }

        @Override
        public void onError(BleError bleError) {
            OnSignErrorCallback onSignErrorCallback = (OnSignErrorCallback) this.onErrorCallback;
            if (bleError.errorId == BleErrorId.BleErrorCode) {
                switch (bleError.statusCode.intValue()) {
                    case LedgerStatusCode.MISSING_SETTING_DATA_ALLOWED:
                        onSignErrorCallback.onTronAppSettingDataNotAllowed(bleError);
                        return;
                    case LedgerStatusCode.MISSING_SETTING_SIGN_BY_HASH:
                        onSignErrorCallback.onTronAppSettingSignHashNotAllowed(bleError);
                        return;
                    case LedgerStatusCode.MISSING_SETTING_CUSTOM_CONTRACT:
                        onSignErrorCallback.onTronAppSettingCustomContractNotAllowed(bleError);
                        return;
                }
            }
            super.onError(bleError);
        }
    }
}
