package com.uway.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 先定义 M 泛型并且继承Tree,之后M在后续的方法快中共用。
 * 给方法定义了一个List<M> 的返回值
 */

public class TreeUtil{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Logger log = LoggerFactory.getLogger(TreeUtil.class);

	public static  <M extends Tree>  List<M> getResult(List<M> src) {
		List<M> parents = new ArrayList<M>();
		List<M> others = new ArrayList<M>();
		for (M ent : src) {
			if (ent.isRoot()) {// 菜单根目录
				M result = ent;
				result.setChildren(new ArrayList<M>());
				parents.add(result);
				log.error("==result"+result.getId()+"==="+result.getText());
			}else{
				others.add(ent);
				log.error("==result"+ent.getId()+"==="+ent.getText());
			}
		}
		buildTree(parents, others);
		return parents;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private  static <M extends Tree> void   buildTree(  List<M> parents, List<M> others) {
		List<M> lastRecord = new ArrayList<M>();
		for (Iterator<M> it = parents.iterator(); it.hasNext();) {
			M vi = it.next();
			if (vi.getId() != null) {
				for (Iterator<M> otherIt = others.iterator(); otherIt.hasNext();) {
					M inVi = otherIt.next();
					if (vi.getId().equals(inVi.getPid())) {
						log.error("==vi==result"+vi.getText()+"==vi=="+vi.getText());
						if (null == vi.getChildren()) {
							vi.setChildren(new ArrayList<M>());
						}
						vi.getChildren().add(inVi);
						log.error("==inVi==result"+inVi.getText()+"==inVi=="+inVi.getText());

						lastRecord.add(inVi);
						otherIt.remove();//移除当前迭代器返回的对象 ==》otherIt.next();
					}
				}
			}
		}
		if (others.size() == 0) {
			return;
		} else {
			buildTree(lastRecord, others);
		}
	}
}