/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import java.util.Arrays;
import sosafe.model.CustomerInfo;

/**
 *
 * @author Z
 */
public class UserAdminAdapter implements UserAdmin {

    @Override
    public boolean login(String name, char[] password) {
        
        CustomerInfo customerInfo;
        try {
            customerInfo = ViewController.getInstance().getCustomerInfoJpaController().findCustomerInfoByEmailAddress(name);
            if (!Arrays.equals(customerInfo.getMasterPassword().toCharArray(), password)) {
                return false;
            }
            ViewController.getInstance().login(customerInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                customerInfo = ViewController.getInstance().getCustomerInfoJpaController().findCustomerInfoByPhoneNumber(name);
                if (!Arrays.equals(customerInfo.getMasterPassword().toCharArray(), password)) {
                    return false;
                }
                ViewController.getInstance().login(customerInfo);
                return true;
            } catch (Exception ee) {
                ee.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public void logoff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean signup() {
        if (ViewController.getInstance().getCustomerInfoJpaController().getCustomerInfoCount() > 0) {
            //Current version does not support multiple users.
            return false;
        } else {
            ViewController.getInstance().signup();
            return true;
        }
    }
    
    @Override
    public boolean createUser(CustomerInfo customerInfo) {
        try {
            ViewController.getInstance().getCustomerInfoJpaController().create(customerInfo);
            ViewController.getInstance().setCurrentUser(customerInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editUser(CustomerInfo customerInfo) {
        try {
            ViewController.getInstance().getCustomerInfoJpaController().edit(customerInfo);
            ViewController.getInstance().setCurrentUser(customerInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
