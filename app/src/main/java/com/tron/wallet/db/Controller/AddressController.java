package com.tron.wallet.db.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.dao.SQLiteOpenHelper;
import com.tron.wallet.db.greendao.AddressDaoDao;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class AddressController {
    public static final int MAX_COUNT = 300;
    private static volatile AddressController mDbController;
    private AddressDaoDao addressInfoDao;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context context = AppContextUtil.getContext();
    private SQLiteOpenHelper mHelper = new SQLiteOpenHelper(this.context, "address.db", null);

    public AddressController() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        this.mDaoMaster = daoMaster;
        DaoSession newSession = daoMaster.newSession();
        this.mDaoSession = newSession;
        this.addressInfoDao = newSession.getAddressDaoDao();
    }

    public static AddressController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (AddressController.class) {
                if (mDbController == null) {
                    mDbController = new AddressController();
                }
            }
        }
        return mDbController;
    }

    public static AddressController getInstance() {
        if (mDbController == null) {
            synchronized (AddressController.class) {
                if (mDbController == null) {
                    mDbController = new AddressController();
                }
            }
        }
        return mDbController;
    }

    private SQLiteDatabase getWritableDatabase() {
        if (this.mHelper == null) {
            this.mHelper = new SQLiteOpenHelper(this.context, "address.db", null);
        }
        return this.mHelper.getWritableDatabase();
    }

    public void insertOrReplace(AddressDao addressDao) {
        this.addressInfoDao.insertOrReplace(addressDao);
    }

    public long insert(AddressDao addressDao) {
        return this.addressInfoDao.insert(addressDao);
    }

    public void update(AddressDao addressDao) {
        AddressDao unique = this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Id.eq(addressDao.getId()), new WhereCondition[0]).build().unique();
        if (unique != null) {
            unique.setName(addressDao.name);
            unique.setAddress(addressDao.address);
            unique.setDescription(addressDao.description);
            this.addressInfoDao.update(unique);
        }
    }

    public List<AddressDao> searchByAddress(String str) {
        return this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Address.eq(str), new WhereCondition[0]).build().list();
    }

    public boolean isNameExist(String str) {
        List<AddressDao> list = this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Name.eq(str), new WhereCondition[0]).build().list();
        return list != null && list.size() > 0;
    }

    public String getNameByAddress(String str) {
        List<AddressDao> list = this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Address.eq(str), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0).getName();
    }

    public AddressDao getAddressDaoByAddress(String str) {
        List<AddressDao> list = this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Address.eq(str), new WhereCondition[0]).build().list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<AddressDao> searchAll() {
        return this.addressInfoDao.queryBuilder().list();
    }

    public void delete(String str) {
        this.addressInfoDao.queryBuilder().where(AddressDaoDao.Properties.Name.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public boolean isAddressCountMax() {
        return this.addressInfoDao.queryBuilder().buildCount().count() >= 300;
    }
}
