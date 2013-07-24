package org.learning.j2ee.supermarket.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class CTable extends JTree {
    public CTable() {
    	  
    	  DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("基本档案管理");
          DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode("供货商管理");
          DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode("销售商管理");
          DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode("活泼档案管理");
          DefaultMutableTreeNode childNode4 = new DefaultMutableTreeNode("仓库管理");
          DefaultMutableTreeNode childNode5 = new DefaultMutableTreeNode("人员管理");
          rootNode.add(childNode1);
          rootNode.add(childNode2);
          rootNode.add(childNode3);
          rootNode.add(childNode4);
          rootNode.add(childNode5);
          
          this.setExpandedState(new TreePath(rootNode), true);
          this.expandRow(0);


    }    
 
    
    
}
