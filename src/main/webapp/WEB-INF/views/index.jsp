<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<head>
<title>Лента новостей</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head> 
<body>
<div id="page">
  
  <div id="header"> 
    <div id="searchPanel">
						<form action="/" method="get" id="searchForm">

							<div class="input-group">
								<input type="text" value="${search}" name="search"
									id="search_query" class="form-control"> <span>
									<button id="btn-search" type="submit">
										Найти <i class="fa fa-search"></i>
									</button>
								</span>
							</div>
						</form>
					</div>
  </div>
  
  <div id="top_curve">
  </div>
  <div id="content">
    <div class="inner_padding">
      <div id="left">
        <h4>Список Новостей</h4>
        
        <c:forEach items="${newsList}" var="newsItem">
                <h3><a href="${pageContext.request.contextPath}/news/${newsItem.id}">${newsItem.title}</a></h3>
                <p class="box">${newsItem.content} </p>
                <p>${newsItem.date}</p>
                <p id="edit"><a href="${contextPath}/update_news?id=${newsItem.id}">Редактировать</a>
                <a href="${contextPath}/delete_news?id=${newsItem.id}">Удалить</a></p>
        </c:forEach>
        
      </div>
      <div id="right"> 
        <h4>Категории</h4>
        <ul>
        <c:forEach items="${categoryList}" var="categoryItem">
            <li><a href="${pageContext.request.contextPath}/?category=${categoryItem.id}">${categoryItem.title} </a></li>
        </c:forEach>
        </ul>
       
       <div id=addNews>
					<a id="btn-primary"
						href="${pageContext.request.contextPath}/add_news">Добавить
						</a>
				</div>
      </div>
      <div class="spacer"></div>
    </div>
  </div>
  <div id="bottom_curve"> </div>
  
  <div id="bottom">
    <div class="inner_padding">
    </div>
  </div>
</div>
</body>
</html>
