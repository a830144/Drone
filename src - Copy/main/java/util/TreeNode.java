package util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode{	
	
	public TreeNode(Integer nodeId, String type, String code, String codeContent, Integer parentNodeId) {
		super();
		this.nodeId = nodeId;
		this.type = type;
		this.code = code;
		this.codeContent = codeContent;
		this.parentNodeId = parentNodeId;
		
	}
	List<TreeNode> children = new ArrayList<TreeNode>();
	TreeNode parentNode;
	public TreeNode(Integer nodeId, String codeContent) {
		super();
		this.nodeId = nodeId;
		this.codeContent = codeContent;
	}
	public TreeNode(String codeContent) {
		super();
		this.codeContent = codeContent;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
	@Override
	public String toString() {
		return "TreeNode [nodeId=" + nodeId + ", type=" + type + ", code=" + code + ", codeContent=" + codeContent
				+ ", parentNodeId=" + parentNodeId + "]";
	}
	public TreeNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeContent() {
		return codeContent;
	}
	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}
	public Integer getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(Integer parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public TreeNode(Integer nodeId, Integer parentNodeId) {
		super();
		this.nodeId = nodeId;
		this.parentNodeId = parentNodeId;
	}
	Integer nodeId;
	String type;
	String code;
	String codeContent;
	Integer parentNodeId;
	
	
}