package OSMsoft.AdminAction;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 在部门树中组织时，每一个树节点的结构
 *
 * @author saulzhang
 */

public class TreeNode implements Serializable {
    /**
     * 树节点id
     * 为了兼容多种情况，使用String类型
     */
    private String id;
    /**
     * 树节点上级id
     */
    private String parentId;

    private String name;


    private String url;

    // 菜单图标
    private String icon;

    private ArrayList<TreeNode> children = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    public void addChildren(TreeNode treeNode) {
        this.children.add(treeNode);
    }

    public TreeNode getTreeNodeByIndex(int index) {
        try {
            return this.children.get(index);
        } catch (Exception e) {
            System.out.println("This child is not exist!");
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override

    public String toString() {

        return "Department [id=" + id + ", parentId=" + parentId + ", name=" + name + ", url=" + url + ", icon=" + icon

                + ", children=" + children + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof TreeNode) {

            TreeNode node = (TreeNode) obj;

            return (id.equals(node.id));
        }
        return super.equals(obj);

    }
}