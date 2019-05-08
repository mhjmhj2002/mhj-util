Select Count(1) /*
  a.Id_Case_Acao Id_Case_Acao,
  c.Dt_Incoming Data_Entrada,
  c.Dt_Compra Data_Transacao,
  c.Id_Case Id_Case,
  Case When c.id_tipo_processo = 3 And c.cd_bandeira In (7,9) Then '08'
  When c.id_tipo_processo = 3 And c.cd_bandeira = 2 Then '02'
  Else Decode(c.Id_Tipo_Processo,1,'01',11,'03',5,'04',7,'05',12,'06',2,'07')
  End Tipo_Processo,
  c.Nu_Reference Reference_Number,
  Substr(c.Nu_Cartao, 0, 6) Bin, 
  c.Nu_Estab_Comercial Numero_Ec,
  c.Cd_Tipo_Produto Card_Type,
  Case When c.cd_tipo_produto In (10, 12, 20, 21, 70, 72) Then 'C'
  When c.cd_tipo_produto In (11, 71) Then 'D'
  Else 'C' End Plataforma,
  c.Cd_Moeda Moeda_Transacao,
  c.Vl_Total Valor_Chargeback,
  Case When c.Cd_Tipo_Produto In (12, 72, 21) Then --parcelado Master, Elo, Diners
  Coalesce(Par.Vl_Total_Venda,Au.Vl_Autorizacao,Ct.Vl_Total_Parcela)
  Else Null End Valor_Plano,
  c.Nu_Plano Numero_Plano,
  Case When c.Cd_Tipo_Produto In (12, 72, 21) Then
  c.Nu_Parcela Else Null End Numero_Parcela,
  Case When c.Cd_Tipo_Produto In (12, 72, 21) Then
  Par.Qt_Total_Parcela Else Null End Total_Parcelas,
  Decode(a.Id_Tipo_Acao,9,'01',3,'02',4,'03',5,'04',6,'05',8,'06',27,'07') 
  Cod_Acao_Tratamento,
  a.Vl_Acao Valor_Acao_Tratamento,
  At.Vl_Ajuste Valor_Ajuste,
  Aj.Cd_Ajuste Cod_Ajuste, 
  Cc.Cd_Classificacao_Contabil Cod_Classificacao,
  Cc.Dc_Classificacao_Contabil Desc_Classificacao,
  cas.dh_acao Data_Acao,
  At.Cd_Status_Ajuste,
  At.Dt_Efetivacao_Ajuste Data_Efetiv_Ajuste,
  c.Cd_Bandeira Bandeira  --*/

From 
  Tbstcr_Case c
  Inner Join Tbstcr_Case_Acao a 
    On c.Id_Case = a.Id_Case 
  Inner Join Tbstcr_Case_Acao_Status cas
    On a.Id_Case_Acao = cas.Id_Case_Acao 
    And cas.Id_Tipo_Status_Acao In (1, 2, 7)
  Left Join Tbstcr_Classificacao_Contabil Cc 
    On a.Id_Classificacao_Contabil = Cc.Id_Classificacao_Contabil
  Left Join Tbstcr_Case_Autorizacao Au 
    On c.Id_Case = Au.Id_Case
  Left Join Tbstcr_Acao_Tratamento At 
    On a.Id_Case_Acao = At.Id_Case_Acao
    And At.Cd_Status_Ajuste = 'A'
  Left Join Tbstcr_Case_Dado_Parcelado Par 
    On c.Id_Case = Par.Id_Case
  Left Join Tbstcr_Case_Transacao Ct 
    On c.Id_Case = Ct.Id_Case
    And c.Nu_Reference = Ct.Nu_Reference
    And c.Nu_Parcela = Ct.Nu_Parcela 
  Left Join tbstcr_tipo_ajuste Aj
    On At.Id_Ajuste = Aj.Id_Ajuste

Where 
  (c.Id_Tipo_Processo In (1, 11, 5, 12) --chb, chbVoucher, reversaoChb, reversaoChbVoucher 
  Or (c.Id_Tipo_Processo In (3, 7) And c.Cd_Bandeira = 2) --arb(Master), reversaoArb(Master)
  Or (c.Id_Tipo_Processo = 3 And c.Cd_Bandeira In (7, 9) And a.Id_Tipo_Acao In (3, 4, 5, 6))) --preArbitragem(ELO/Diners)
  
  And (a.Id_Tipo_Acao = 9 --incoming
  Or (a.Id_Tipo_Acao In (3, 4) And c.Id_Tipo_Status_Case = 3 And At.cd_status_ajuste = 'A') --debitoEC, creditoEC (finalizado e aprovado)
  Or (a.id_tipo_acao In (8, 27) And c.Id_Tipo_Status_Case = 3 And a.id_tipo_status_acao In (5, 6, 10)) --reap, reversaoReap (finalizado e outgoing ok)
  Or (a.id_tipo_acao In (5, 6) And c.Id_Tipo_Status_Case = 3)) --prejuizo, receita (finalizado)
  And a.In_Envd_Intg_Cont = 'N'

Order By 
  c.Id_Case, a.Id_case_acao;

Select * From tbstcr_proc_outgoing_intg_cont;
--Update tbstcr_case_acao a Set a.in_envd_intg_cont = 'N';