<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="UTF-8"%>

            <div class="modal fade" id="modal-cancelar">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Cancelar?</h4>
                        </div>
                        <div class="modal-body">
                            <p>Deseja realmente cancelar?&hellip;</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="history.go(-1)"><span class="glyphicon glyphicon-ok"></span> Sim</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Não</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            
            <div class="modal fade" id="modal-sair">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Sair</h4>
                        </div>
                        <div class="modal-body">
                            <p>Deseja realmente sair ?&hellip;</p>
                        </div>
                        <div class="modal-footer">
                            <a href="<c:url value="/logout"/>" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Sim</a>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Não</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            
            <div class="modal fade" id="modal-confirmar">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Excluir?</h4>
                        </div>
                        <div class="modal-body">
                            <p>Deseja realmente excluir?&hellip;</p>
                        </div>
                        <div class="modal-footer">
                            <a id="confirma-exclusao" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Sim</a>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Não</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->