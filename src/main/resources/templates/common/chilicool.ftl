<!--select标签-->
<#macro select id="" name="" value="" style="" class="" attributes="" other="" typeCode="">
    <#if typeCode!="">
    <select id="${id}" name="${name}" value="${value}" style="${style}" class="${class} typecode_for_select"
            typeCode="${typeCode}" ${attributes}>
    </select>
    ${other}
    </#if>
</#macro>

<#--
 * message
 * Macro to translate a message code into a message.
 -->
<#macro message typeCode paramCode>${getCodeDesc(typeCode, paramCode)}</#macro>