package OSMsoft.AdminAction;

import OSMsoft.DAO.DepartmentTreeDAO;

import java.util.ArrayList;


/**
 * 获取DepartmentTreeDao查询到的所有部门节点信息，
 * 利用递归的方法将其组织成为树状的结构。
 *
 * @author saulzhang
 */

public class TreeServiceImp {

    public DepartmentTreeDAO departmentTreeDAO = new DepartmentTreeDAO();

    public ArrayList<TreeNode> testQueryDepList() {
        // 原始的数据
        ArrayList<TreeNode> rootDep = departmentTreeDAO.selectAllDep();

        // 查看结果
//        for (TreeNode dep : rootDep) {
//            System.out.println(dep);
//        }
        // 最后的结果
        ArrayList<TreeNode> depList = new ArrayList<TreeNode>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootDep.size(); i++) {
            // 一级菜单parentId为0
            if (rootDep.get(i).getParentId().equals("0")) {

                //System.out.println(rootDep.get(i));

                depList.add(rootDep.get(i));
            }

        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (TreeNode dep : depList) {
            dep.setChildren(getChild(dep.getId(), rootDep));
        }

        for (TreeNode dep : depList) {
            System.out.println(dep);
        }

        return depList;
        /*Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("menu", depList);
        System.out.println(gson.toJson(jsonMap));
        */
    }

    /**
     * 递归查找子菜单
     *
     * @param id      当前菜单id
     * @param rootDep 要查找的列表
     * @return 组织成为树状结构的arraylist
     */
    private ArrayList<TreeNode> getChild(String id, ArrayList<TreeNode> rootDep) {
        // 子菜单
        ArrayList<TreeNode> childList = new ArrayList<>();
        for (TreeNode dep : rootDep) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (dep.getParentId().equals(id)) {
                childList.add(dep);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (TreeNode depSon : childList) {// 没有url子菜单还有子菜单
//            System.out.print("second Here:"+depSon+"depSon.getUrl:"+depSon.getUrl()+" ");
//            System.out.println(depSon.getUrl().isEmpty());
//            System.out.println();
            if (depSon.getUrl().equals("no resources")) {
                // 递归
                depSon.setChildren(getChild(depSon.getId(), rootDep));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
