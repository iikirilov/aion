package org.web3j.aion.abi

import org.aion.avm.core.util.ABIUtil
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.utils.Numeric

@Suppress("UNCHECKED_CAST")
object AbiFunctionEncoder : FunctionEncoder() {

    override fun encodeFunction(function: Function): String {
        val params = function.inputParameters.map { it.aionValue }.toTypedArray()
        return Numeric.toHexString(ABIUtil.encodeMethodArguments(function.name, *params))
    }

    override fun encodeParameters(parameters: List<Type<*>>): String {
        val params = parameters.map { it.aionValue }.toTypedArray()
        return Numeric.toHexString(ABIUtil.encodeDeploymentArguments(*params))
    }
}

class AionEncodingException(message: String) : RuntimeException(message)