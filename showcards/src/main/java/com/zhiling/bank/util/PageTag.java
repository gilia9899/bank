package com.zhiling.bank.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport{
	
	
	//所有 普通的a data-type=page  上一页  data-type=prepage  下一页 nextpage
	
	/** 当前页码 */
	private int pageIndex;
	/** 每页显示的数量 */
	private int pageSize;
	/** 总记录条数 */
	private int recordCount;
	/** 定义总页数 */
	private int pageCount = 0;
	
	@Override
	public void doTag() throws JspException, IOException {
		/** 定义它拼接是终的结果 */
		StringBuilder res = new StringBuilder();
		/** 定义它拼接中间的页码 */
		StringBuilder str = new StringBuilder();
		/** 判断总记录条数 */
		if (recordCount > 0){   //1499 / 15  = 100
			/** 需要显示分页标签，计算出总页数 需要分多少页 */
			pageCount = (this.recordCount - 1) / this.pageSize + 1; 
			
			/** 判断上一页或下一页需不需要加a标签 */
			if (this.pageIndex == 1){ // 首页
				str.append("<span class='disabled'>上一页</span>&nbsp;&nbsp;&nbsp;&nbsp;");
				
				/** 计算中间的页码 */
				this.calcPage(str);
				
				/** 下一页需不需要a标签 */
				if (this.pageIndex == pageCount){
					/** 只有一页 */
					str.append("<span class='disabled'>下一页</span>");
				}else{
					str.append("<a data-type='nextpage' href='javascript:void(0)'>下一页</a>");
				}
			}else if (this.pageIndex == pageCount){ // 尾页
				str.append("<a data-type='prepage' href='javascript:void(0)'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				
				/** 计算中间的页码 */
				this.calcPage(str);
				
				str.append("<span class='disabled'>下一页</span>");
			}else{ // 中间
				str.append("<a data-type='prepage' href='javascript:void(0)'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				
				/** 计算中间的页码 */
				this.calcPage(str);
				
				str.append("<a data-type='nextpage' href='javascript:void(0)'>下一页</a>");
			}
			
			/** 拼接其它的信息 */
			res.append("<table width='100%' align='center' style='font-size:13px;'>");
			res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
			res.append("&nbsp;跳转到&nbsp;&nbsp;<input style='text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none' type='text' size='2' id='pager_jump_page_size'/>");
			res.append("&nbsp;<input type='button' style='text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none' value='确定' id='pager_jump_btn'/>");
			res.append("</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>");
			/** 开始条数 */
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			/** 结束条数 */
			int endNum = (this.pageIndex == this.pageCount) ? this.recordCount : this.pageIndex * this.pageSize;
			
			res.append(this.pageIndex+"/"+this.pageCount+"&nbsp;页，&nbsp;"+"总共<font color='red'>"+ this.recordCount +"</font>条记录，当前显示"+ startNum +"-"+ endNum +"条记录。");
			res.append("</td></tr>");
			res.append("</table>");
			/** 给跳转框 的值 做些限制   如果<=0  等于0,>=pagecount 等于pagecount,非数字或负数 等于 1 */
			res.append("<script> ");
			res.append("var pagejumpbtn = document.getElementById('pager_jump_btn');\r\n" + 
					"		pagejumpbtn.onclick = function(){ ");
			res.append("var pagecount = parseInt("+this.pageCount+");");
			res.append("var pagejumppagesize = document.getElementById('pager_jump_page_size').value;\r\n");
			res.append("pagejumppagesize = parseInt(pagejumppagesize);if(typeof pagejumppagesize != 'number'){pagejumppagesize=1;}");
			res.append("pagejumppagesize = pagejumppagesize >= pagecount ? pagecount:pagejumppagesize;\r\n" + 
					"			pagejumppagesize = pagejumppagesize <= 0 ? 1:pagejumppagesize;");
			res.append("document.getElementById('pager_jump_page_size').value = pagejumppagesize;}");
			res.append("</script>");
			
		}else{
			res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>0/0&nbsp;页，&nbsp;总共<font color='red'>0</font>条记录，当前显示0-0条记录。</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}
	
	
	/** 计算中间页码的方法 */
	private void calcPage(StringBuilder str) {
		/** 判断总页数 */
		if (this.pageCount <= 11){
			/** 一次性显示全部的页码 */
			for (int i = 1; i <= this.pageCount; i++){
				if (this.pageIndex == i){
					/** 当前页码 */
					str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
				}else{
					str.append("<a data-type='page' href='javascript:void(0)'>"+ i +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				}
			}
		}else{
			/** 靠近首页 */
			if (this.pageIndex <= 8){
				for (int i = 1; i <= 10; i++){
					if (this.pageIndex == i){
						/** 当前页码 */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
					}else{
						str.append("<a data-type='page' href='javascript:void(0)'>"+ i +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
					}
				}
				str.append("...&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("<a data-type='page' href='javascript:void(0)'>"+ this.pageCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			/** 靠近尾页 */
			else if (this.pageIndex + 8 >= this.pageCount){
				str.append("<a data-type='page' href='javascript:void(0)'>1</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("...&nbsp;&nbsp;&nbsp;&nbsp;");
				
				for (int i = this.pageCount - 10; i <= this.pageCount; i++){
					if (this.pageIndex == i){
						/** 当前页码 */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
					}else{
						str.append("<a data-type='page' href='javascript:void(0)'>"+ i +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
					}
				}
			}
			/** 在中间 */
			else{
				
				str.append("<a data-type='page' href='javascript:void(0)'>1</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("...");
				
				for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++){
					if (this.pageIndex == i){
						/** 当前页码 */
						str.append("<span class='current'>"+ i +"</span>&nbsp;&nbsp;&nbsp;&nbsp;");
					}else{
						
						str.append("<a data-type='page' href='javascript:void(0)'>"+ i +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
					}
				}
				
				str.append("...");
				str.append("<a data-type='page' href='javascript:void(0);'>"+ this.pageCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
		}
	}


	public int getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getRecordCount() {
		return recordCount;
	}


	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	
	
}
