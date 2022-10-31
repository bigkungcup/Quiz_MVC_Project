<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sanit
  Date: 18/7/22
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question</title>
    <link rel="stylesheet" href="css/style.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <div class="card">
        <c:choose>
            <c:when test='${id=="new"}'>
                <h3 class="label">New Question</h3>
                <p>
                <form action="manage-question" method="post">
                    <span class="label">Question : </span>
                    <input type="text" name="text"  />
                    <input type="hidden" name="id" value="new" />
                    <input type="hidden" name="action" value="add-question" />
                    <input type="submit" value="Save">
                </form>
                </p>
                <a href="list-questions">Back</a>
            </c:when>
            <c:otherwise>
                <h3 class="label">QID : ${question.id}</h3>
                <p>
                <div id="edit-ques-form" class="hide">
                    <form action="manage-question" method="post">
                        <span class="label">Question : </span>
                        <input name="text" value="${question.question}" />
                        <input type="hidden" name="action" value="edit-question" />
                        <input type="hidden" name="id" value="${question.id}" />
                        <input type="submit" value="Save">
                    </form>
                </div>
                <div id="edit-ques">
                    <span class="label">Question : </span>
                        ${question.question}
                    <span class="action">[ <a id="edit-ques-but" href="#">Edit</a> ]</span>
                </div>
                </p>
                <ol type="a">
                    <c:forEach items="${question.answers}" var="answer" varStatus="as">
                        <li>
                            <div id="edit-answer-form-${as.index}" class="hide">
                                <form action="manage-question" method="post">
                                    <input type="text" name="text" size="30" placeholder="edit answer" value="${answer.answer}"/>
                                    <input id="isCorrect-edit" type="checkbox" name="isCorrect" ${answer.isCorrect==1?"checked":""} >
                                    <label for="isCorrect-edit">Correct?</label>
                                    <input type="hidden" name="action" value="edit-answer" />
                                    <input type="hidden" name="id" value="${answer.id}" />
                                    <input type="submit" value="Save">
                                </form>
                            </div>
                            <div id="edit-answer-${as.index}">
                          <span ${ answer.isCorrect==1?'class="correct"':""}>
                                  ${answer.answer}</span><span class="action">
                          [ <a href="#" onclick="editAnswer(${as.index})">Edit</a> |
                            <a href="manage-question?id=${answer.id}&action=remove-answer">Delete</a> ]
                          </span>
                            </div>
                        </li>
                    </c:forEach>
                </ol>

                <div id="new-answer" class="hide">
                    <form action="manage-question" method="post">
                        <input type="text" name="text" size="30" placeholder="add new answer" />
                        <input id="isCorrect-add" type="checkbox" name="isCorrect" >
                        <label for="isCorrect-add">Correct?</label>
                        <input type="hidden" name="action" value="add-answer" />
                        <input type="hidden" name="id" value="${question.id}" />
                        <input type="submit" value="add">
                    </form>
                </div>

                <a href="list-questions">Back</a>&nbsp;&nbsp;<a id="add-but" href="#">Add Answer</a>
            </c:otherwise>
        </c:choose>
    </div>
    <script>
        $('#add-but').click(function(){
            if($('#new-answer').hasClass('hide')){
                $('#add-but').text("Close Answer") ;
            }else {
                $('#add-but').text("Add Answer") ;
            }
            $('#new-answer').toggleClass('hide') ;
        }) ;
        $('#edit-ques-but').click(function(){
            if($('#edit-ques-form').hasClass('hide')){
                $('#edit-ques').addClass('hide') ;
            }else{
                $('#edit-ques').removeClass('hide') ;
            }
            $('#edit-ques-form').toggleClass('hide') ;
        }) ;
        function editAnswer(index) {
            $('#edit-answer-'+index).toggleClass('hide') ;
            $('#edit-answer-form-'+index).toggleClass('hide') ;
        }
    </script>
</body>
</html>
