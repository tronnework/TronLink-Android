package com.tron.wallet.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.BiConsumer;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
public class AccountPermissionUtils {
    public static boolean isAccountPermissionMultiSign(Protocol.Account account) {
        SparseArray<List<String>> accountPermissionMap = getAccountPermissionMap(account);
        if (accountPermissionMap == null) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < accountPermissionMap.size(); i2++) {
            List<String> list = accountPermissionMap.get(accountPermissionMap.keyAt(i2));
            if (list != null && !list.isEmpty() && (list.size() > 1 || !TextUtils.equals(StringTronUtil.encode58Check(account.getAddress().toByteArray()), list.get(0)))) {
                i++;
            }
        }
        return i > 0;
    }

    public static SparseArray<List<String>> getAccountPermissionMap(Protocol.Account account) {
        if (account == null) {
            return null;
        }
        SparseArray<List<String>> sparseArray = new SparseArray<>();
        sparseArray.put(Protocol.Permission.PermissionType.Owner.getNumber(), getTypedPermission(account.getOwnerPermission()));
        sparseArray.put(Protocol.Permission.PermissionType.Witness.getNumber(), getTypedPermission(account.getWitnessPermission()));
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(account.getActivePermissionList()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                Collection.-EL.stream(AccountPermissionUtils.getTypedPermission((Protocol.Permission) obj)).forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj2) {
                        AccountPermissionUtils.lambda$getAccountPermissionMap$0(r1, (String) obj2);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        sparseArray.put(Protocol.Permission.PermissionType.Active.getNumber(), arrayList);
        return sparseArray;
    }

    public static void lambda$getAccountPermissionMap$0(List list, String str) {
        if (list.contains(str)) {
            return;
        }
        list.add(str);
    }

    public static void isAccountPermissionMultiSign(Context context, Protocol.Account account, BiConsumer<Boolean, String> biConsumer) {
        SparseArray<List<String>> accountPermissionMap = getAccountPermissionMap(account);
        if (accountPermissionMap == null) {
            return;
        }
        String str = "";
        int i = 0;
        for (int i2 = 0; i2 < accountPermissionMap.size(); i2++) {
            int keyAt = accountPermissionMap.keyAt(i2);
            List<String> list = accountPermissionMap.get(keyAt);
            if (list != null && !list.isEmpty() && (list.size() > 1 || !TextUtils.equals(StringTronUtil.encode58Check(account.getAddress().toByteArray()), list.get(0)))) {
                i++;
                if (TextUtils.isEmpty(str)) {
                    str = buildPermissionContent(context, keyAt, list);
                }
            }
        }
        if (i > 1) {
            str = context.getString(R.string.permission_assigned_to, Integer.valueOf(i));
        }
        if (biConsumer != null) {
            try {
                biConsumer.accept(Boolean.valueOf(i > 0), str);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    private static String buildPermissionContent(Context context, int i, List<String> list) {
        boolean z = list.size() == 1;
        String str = z ? list.get(0) : "";
        return i == Protocol.Permission.PermissionType.Owner.getNumber() ? z ? context.getString(R.string.owner_permission_assigned_to_single, str) : context.getString(R.string.owner_permission_assigned_to_multi, Integer.valueOf(list.size())) : i == Protocol.Permission.PermissionType.Witness.getNumber() ? z ? context.getString(R.string.witness_permission_assigned_to_single, str) : context.getString(R.string.witness_permission_assigned_to_multi, Integer.valueOf(list.size())) : z ? context.getString(R.string.active_permission_assigned_to_single, str) : context.getString(R.string.active_permission_assigned_to_multi, Integer.valueOf(list.size()));
    }

    private static List<String> getTypedPermission(Protocol.Permission permission) {
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(permission.getKeysList()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                arrayList.add(StringTronUtil.encode58Check(((Protocol.Key) obj).getAddress().toByteArray()));
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public static boolean checkMulti(String str) {
        try {
            if (StringTronUtil.isEmpty(str)) {
                return false;
            }
            return isAccountPermissionMultiSign(TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check(str), false));
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean checkMulti(List<String> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    boolean z = false;
                    for (String str : list) {
                        if (checkMulti(str)) {
                            z = true;
                        }
                    }
                    return z;
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return false;
    }
}
