package util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DFS
{
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public List list = new LinkedList();
	public DFS() {
		super();
	}

	DFS(Graph g,int s,Map map){
		marked = new boolean[g.getV()+1];
		edgeTo = new int[g.getV()+1];
		dfs(g,s,map);
	}
	
	private void dfs(Graph G,int v,Map map){
		marked[v] = true;
		TreeNode node = (TreeNode)map.get(v);
		System.out.println(node.codeContent+" ");
		Iterator iter = G.adj(v);
		while(iter.hasNext()){
			int w = (int)iter.next();
			if(!marked[w]){
				dfs(G,w,map);
				edgeTo[w] = v;
			}
		}
	}
	
	public void preorderPrint(TreeNode root){
		System.out.print(" " + root);
		list.add(root);
        Iterator iter = root.children.iterator();
        while (iter.hasNext()) {
            preorderPrint((TreeNode) iter.next());
        }
	}

	public List getList() {
		return list;
	}

}