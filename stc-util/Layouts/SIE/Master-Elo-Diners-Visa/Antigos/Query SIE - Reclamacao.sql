With pri_atendimento As (
   Select id_case_reclamacao_debito, Min (id_atendimento) id_atendimento
   From tbstcr_atendimento
   Group By id_case_reclamacao_debito
), grupo_negociacao As (
   Select Distinct
   ec.nu_estab_comercial nu_estab_comercial, 'S' As grupo, nd.dc_negociacao_debito_ec nome
   From tbstcr_negociacao_debito_ec nd
   Inner Join tbstcr_estab_comercial ec On nd.id_estab_comercial = ec.id_estab_comercial
   Where nd.in_status = 'A'
   Union
   Select Distinct
   ec.nu_estab_comercial nu_estab_comercial, 'S' As grupo, nd.dc_negociacao_debito_ec nome
   From tbstcr_negociacao_debito_ec nd
   Inner Join tbstcr_negociacao_debito_ec_ec ne On nd.id_negociacao_debito_ec = ne.id_negociacao_debito_ec
   Inner Join tbstcr_estab_comercial ec On ne.id_estab_comercial = ec.id_estab_comercial
   Where nd.in_status = 'A'
), ult_debito_chb As (
   Select a.id_case, Max(a.id_case_acao) id_case_acao
   From tbstcr_case_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.id_case = r.id_case
   Where a.id_tipo_acao = 3
   Group By a.id_case
), ult_debito_fee As (
   Select a.cd_feec, Max(a.id_fee_acao) id_fee_acao
   From tbstcr_fee_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.cd_feec = r.cd_feec
   Where a.id_tipo_acao_fee = 4
   Group By a.cd_feec
), acao_trat_chb As (
   Select a.id_case, Max(a.id_case_acao) id_case_acao
   From tbstcr_case_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.id_case = r.id_case
   Where a.id_tipo_acao In (5, 8, 20, 29, 32) --5 Prejuízo, 8 2ª Apresentação, 20 Gera Cobrança pela Cielo, 29 Finalizada, 32 Devolução
   Group By a.id_case
), acao_trat_fee As (
   Select a.cd_feec, Max(a.id_fee_acao) id_fee_acao
   From tbstcr_fee_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.cd_feec = r.cd_feec
   Where a.id_tipo_acao_fee In (1, 2, 12) --1 Devolução, 2 Prejuízo, 12 Finalização
   Group By a.cd_feec
), status_acao_chb As (
   Select s.id_case_acao, Min(s.id_case_acao_status) id_case_acao_status
   From tbstcr_case_acao_status s
   Inner Join tbstcr_case_acao a On s.id_case_acao = a.id_case_acao
   Inner Join tbstcr_case_reclamacao_debito r On a.id_case = r.id_case
   Group By s.id_case_acao
), acao_estorno_chb As (
   Select a.id_case, Max(a.id_case_acao) id_case_acao
   From tbstcr_case_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.id_case = r.id_case
   Where a.id_tipo_acao = 4
   Group By a.id_case
), acao_estorno_fee As (
   Select a.cd_feec, Max(a.id_fee_acao) id_fee_acao
   From tbstcr_fee_acao a
   Inner Join tbstcr_case_reclamacao_debito r On a.cd_feec = r.cd_feec
   Where a.id_tipo_acao_fee = 5
   Group By a.cd_feec
), cob_amig_trat As (
   Select Distinct ca.id_case_original, ca.in_acao_retorno
   From tbstcr_cobranca_amigavel ca
   Inner Join tbstcr_case_reclamacao_debito r On ca.id_case_original = r.id_case
   Where ca.in_acao_retorno = 'A' 
   And ca.id_case_original Is Not Null
)
Select --Count(1) /* 781
   rd.cd_referencia referencia,
   rd.cd_evento evento,
   rd.cd_dependencia dependencia,
   At.dt_atendimento data_solicitacao,
   rd.cd_matriz ec_matriz,
   rd.nu_estab_comercial ec_reclamacao,
   rd.cd_moeda moeda_ec,
   rtrim(ltrim(rd.cd_status_ec)) situacao_ec,
   rd.nm_fantasia nome_ec,
   Case
      When gr.grupo = 'S' Then 'S'
      Else 'N' End grupo_negociacao,
   gr.nome nome_grupo,
   Case
      When rd.id_tipo_status_reclamacao = 3 Then 'F'
      Else 'P' End situacao,
   decode(rd.id_tipo_status_reclamacao, 1, 'A', 2, 'P', 3,
      Null, 4, 'C', 5, 'R', 6, 'J', 7, 'N', 8, 'E', 9, 'X',
      10, 'O', 11, 'Y') det_sit_pendente,
   Case
      When rd.id_case Is Not Null And ch.id_tipo_processo In (1, 3, 5, 7, 11, 12) Then 'C'
      When rd.id_case Is Not Null And ch.id_tipo_processo In (2, 6) Then 'A'
      When rd.cd_feec Is Not Null Then 'F'
      Else Null End tipo_case_associado,
   Case
      When rd.id_case Is Not Null And ch.id_tipo_processo In (1, 3, 5, 7, 11, 12) Then rd.id_case
      When rd.id_case Is Not Null And ch.id_tipo_processo In (2, 6) Then ca.id_cobranca_amigavel
      When rd.cd_feec Is Not Null Then rd.cd_feec
      Else Null End numero_case,
   Case
      When rd.id_case Is Not Null Then ch.cd_razao_chargeback
      When rd.cd_feec Is Not Null Then fe.cd_razao_chargeback
      Else Null End cod_razao,
   Case
      When rd.id_case Is Not Null Then aj.vl_ajuste
      When rd.cd_feec Is Not Null Then af.vl_ajuste
      Else Null End vlr_ajuste_debito,
   Case
      When rd.id_case Is Not Null Then ch.cd_tipo_produto
      Else Null End cod_produto,
   Case
      When rd.id_case Is Not Null Then ch.cd_bandeira
      When rd.cd_feec Is Not Null Then fe.cd_bandeira
      Else Null End bandeira,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then 'I'
      When tca.id_tipo_acao = 20 And cat.in_acao_retorno = 'A' Then 'C'
      When rd.id_case Is Not Null Then
         decode(tca.id_tipo_acao, 5, 'P', 8, 'R', 29, 'F', 32, 'D', 'F')
      When rd.cd_feec Is Not Null Then
         decode(tfa.id_tipo_acao_fee, 1, 'D', 2, 'P', 12, 'F', 'F')
      Else 'F' End tipo_acao_trat,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And cas.dh_acao Is Not Null Then cas.dh_acao
      When rd.cd_feec Is Not Null And tfa.dh_acao Is Not Null Then tfa.dh_acao
      Else rd.dt_fnl_rcl_deb End data_acao,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And cas.id_usuario Is Not Null Then uc.nm_usuario
      When rd.cd_feec Is Not Null And tfa.id_usuario Is Not Null Then us.nm_usuario
      Else uf.nm_usuario End usuario,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null Then taj.cd_ajuste
      When rd.cd_feec Is Not Null Then taf.cd_ajuste
      Else Null End cod_ajuste_credito,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And tca.id_tipo_acao = 5 Then ccc.cd_classificacao_contabil
      When rd.cd_feec Is Not Null And tfa.id_tipo_acao_fee = 2 Then ccf.cd_classificacao_contabil
      Else Null End cod_classificacao,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And tca.id_tipo_acao = 5 Then ccc.dc_classificacao_contabil
      When rd.cd_feec Is Not Null And tfa.id_tipo_acao_fee = 2 Then ccf.dc_classificacao_contabil
      Else Null End desc_classificacao,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And tca.id_tipo_acao = 8 Then rcc.cd_razao_chargeback
      Else Null End cod_razao_reap,
   Case
      When rd.id_tipo_status_reclamacao <> 3 Then Null
      When rd.id_case Is Not Null And tca.id_tipo_acao = 8 Then rcc.dc_razao_chargeback_pt
      Else Null End cod_desc_reap,
   decode(rd.id_tipo_status_reclamacao, 3, rd.cd_dependencia_nova, Null) nova_dep
   --*/
From 
   tbstcr_case_reclamacao_debito rd 
   Inner Join pri_atendimento pa On rd.id_case_reclamacao_debito = pa.id_case_reclamacao_debito 
   Inner Join tbstcr_atendimento At On pa.id_atendimento = At.id_atendimento 
   Left Join grupo_negociacao gr On rd.nu_estab_comercial = gr.nu_estab_comercial
   Left Join tbstcr_case ch On rd.id_case = ch.id_case 
   Left Join tbstcr_feec fe On rd.cd_feec = fe.cd_feec 
   Left Join tbstcr_cobranca_amigavel ca On rd.id_case = ca.id_case 
   Left Join ult_debito_chb uc On rd.id_case = uc.id_case
   Left Join tbstcr_case_acao aa On uc.id_case_acao = aa.id_case_acao
   Left Join ult_debito_fee uf On rd.cd_feec = uf.cd_feec
   Left Join tbstcr_fee_acao fa On uf.id_fee_acao = fa.id_fee_acao
   Left Join tbstcr_acao_tratamento aj On aa.id_case_acao = aj.id_case_acao 
   Left Join tbstcr_acao_tratamento af On uf.id_fee_acao = af.id_fee_acao
   Left Join acao_trat_chb tc On rd.id_case = tc.id_case
   Left Join tbstcr_case_acao tca On tc.id_case_acao = tca.id_case_acao
   Left Join acao_trat_fee tf On rd.cd_feec = tf.cd_feec
   Left Join tbstcr_fee_acao tfa On tf.id_fee_acao = tfa.id_fee_acao
   Left Join cob_amig_trat cat On rd.id_case = cat.id_case_original
   Left Join status_acao_chb sac On tca.id_case_acao = sac.id_case_acao
   Left Join tbstcr_case_acao_status cas On sac.id_case_acao_status = cas.id_case_acao_status
   Left Join tbstcr_usuario uc On cas.id_usuario = uc.id_usuario
   Left Join tbstcr_usuario us On tfa.id_usuario = us.id_usuario
   Left Join tbstcr_usuario uf On rd.id_usuario_fnl_rcl_deb = uf.id_usuario
   Left Join acao_estorno_chb aec On rd.id_case = aec.id_case
   Left Join tbstcr_acao_tratamento aet On aec.id_case_acao = aet.id_case_acao
   Left Join tbstcr_tipo_ajuste taj On aet.id_ajuste = taj.id_ajuste
   Left Join acao_estorno_fee aef On rd.cd_feec = aef.cd_feec 
   Left Join tbstcr_acao_tratamento aex On aef.id_fee_acao = aex.id_fee_acao
   Left Join tbstcr_tipo_ajuste taf On aex.id_ajuste = taf.id_ajuste
   Left Join tbstcr_classificacao_contabil ccc On tca.id_classificacao_contabil = ccc.id_classificacao_contabil
   Left Join tbstcr_classificacao_contabil ccf On tfa.id_classificacao_contabil = ccf.id_classificacao_contabil
   Left Join tbstcr_razao_chargeback rcc On tca.id_razao_chargeback = rcc.id_razao_chargeback
Where 
   rd.id_tipo_status_reclamacao In (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
   And rd.in_reclm_deb_envd = 'N'
;

Select * From tbstcr_case_reclamacao_debito Order By 1 Desc;
Select * From tbstcr_case_reclamacao_debito Where in_reclm_deb_envd = 'S';
Select * From tbstcr_proc_outgoing_sie Order By 1 Desc;
Delete tbstcr_proc_outgoing_sie Where cd_proc_outgoing_sie In (69,70);
Update tbstcr_case_reclamacao_debito r Set r.in_reclm_deb_envd = 'N';