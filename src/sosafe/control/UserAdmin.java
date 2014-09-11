/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.control;

import sosafe.model.CustomerInfo;

/**
 *
 * @author Z
 */
public interface UserAdmin {
    boolean login(String name, char[] password);
    void logoff();
    boolean signup();
    boolean createUser(CustomerInfo customerInfo);
    boolean editUser(CustomerInfo customerInfo);
}
