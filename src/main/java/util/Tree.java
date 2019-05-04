package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Code;

public class Tree {
	TreeNode root;
	Map map = new HashMap();
	
	
	public Map getMap() {
		return map;
	}

	public Tree(List<Code> list){
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Code code = (Code)iterator.next();			
			TreeNode treeNode = new TreeNode(code.getNodeId(),code.getCodeType(),code.getCode(),code.getCodeContent(),code.getParentNodeId());
			map.put(treeNode.nodeId,treeNode);
			if(code.getParentNodeId()==0){
				root = treeNode;
			}
		}
		addEdges();
	}
	
	public void addEdges(){
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			int nodeId = (int)iterator.next();
			TreeNode treeNode = (TreeNode)map.get(nodeId);			
			if(treeNode.parentNodeId!=0){
				TreeNode parentNode = (TreeNode) map.get(treeNode.parentNodeId);
				treeNode.parentNode =parentNode;
				parentNode.children.add(treeNode);
			}
		}
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public int getLevel(TreeNode node){
		int i=0;
		TreeNode current = node;
		while(current!=null){
			i++;
			current = current.parentNode;
		}
		return i;
	}
}
