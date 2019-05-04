package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import entity.Code;
import service.OtherService;


public class CodeTreeHierarchyHelper {
	
	private Tree tree;
	
	public Tree getTree() {
		return tree;
	}

	private Map virtualIdToNodeId = new HashMap();
	private Map NodeIdToVirtualId = new HashMap();
	private Map NodeIdToNode = new HashMap();
	private Map virtualIdToNode = new HashMap();

	public void convertToTreeNode(List<Code> list){
		Iterator iterator = list.iterator();
		int i = 0;
		while(iterator.hasNext()){
			i++;
			Code code = (Code)iterator.next();
			TreeNode treeNode = new TreeNode(code.getNodeId(),code.getCodeType(),code.getCode(),code.getCodeContent(),code.getParentNodeId());
			virtualIdToNodeId.put(i, treeNode.nodeId);
			NodeIdToVirtualId.put(treeNode.nodeId, i);
			NodeIdToNode.put(treeNode.nodeId, treeNode);
			virtualIdToNode.put(i, treeNode);			
		}
	}
	
	public Graph addEdges(Graph graph,List<Code> list){
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Code code = (Code)iterator.next();
			int parentNodeId = code.getParentNodeId();
			int nodeId = code.getNodeId();
			
			if(parentNodeId!=0){
				int virtualParentNodeId = (int)NodeIdToVirtualId.get(parentNodeId);
				int virtualNodeId = (int)NodeIdToVirtualId.get(nodeId);
				System.out.println("v ::"+virtualParentNodeId +"~ w::"+virtualNodeId );
				graph.addEdge(virtualParentNodeId, virtualNodeId);
			}
		}
		return graph;
	}
	
	public void CodeTreeHierarchy(List<Code> list){
		Graph graph = new Graph(list.size());
		convertToTreeNode(list);
		addEdges(graph,list);
		DFS d = new DFS(graph,1,virtualIdToNode);
	}
	
	public Map codeTreeHierarchyMap(List<Code> list){
		this.tree = new Tree(list);
		Map returnMap = new HashMap();
		Map map = tree.getMap();
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			int key = (int)iterator.next();
			TreeNode node = (TreeNode)map.get(key);
			returnMap.put(node, tree.getLevel(node));
		}
		return returnMap;
	}
	public static void main(String args[]){
	ApplicationContext ctx 
    = new FileSystemXmlApplicationContext(new String[] {
      "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\service-context.xml",
      "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\dataSource-context.xml",
      "D:\\64 bit_IDE &JAVA\\java-neon\\workspace\\Drone\\WebContent\\WEB-INF\\normal\\persistence-context.xml"
      },true);
	OtherService otherService =(OtherService)  ctx.getBean("otherService");
	List<Code> list = otherService.queryCodes("License_type");
	
	CodeTreeHierarchyHelper CodeTreeHierarchyHelper = new CodeTreeHierarchyHelper();
	Map map = CodeTreeHierarchyHelper.codeTreeHierarchyMap(list);
	TreeNode root = CodeTreeHierarchyHelper.getTree().getRoot();
	DFS dfs = new DFS();
	dfs.preorderPrint(root);
	
	}
}
