package com.ekenya.rnd.personalfinance.data.modeldto

import com.ekenya.rnd.personalfinance.R

data class ExpensesModel(
    val imageExpense: Int,
    val titleExpense: String,
    val amountExpense: Int,
    val percentageExpense: String
) {
    companion object {
        fun getExpenseModel(): ArrayList<ExpensesModel> =
            arrayListOf(
                ExpensesModel(
                    imageExpense = R.drawable.ic_fund_transfer_pf,
                    titleExpense = "Funds Transfer",
                    amountExpense = 12000,
                    percentageExpense = "40%"
                ), ExpensesModel(
                    imageExpense = R.drawable.ic_merchant_payment_pf,
                    titleExpense = "Merchant Payment",
                    amountExpense = 4000,
                    percentageExpense = "36%"
                ), ExpensesModel(
                    imageExpense = R.drawable.ic_bill_payment_pf,
                    titleExpense = "Bill Payment",
                    amountExpense = 3000,
                    percentageExpense = "24%"
                ), ExpensesModel(
                    imageExpense = R.drawable.ic_withdraw_money_pf,
                    titleExpense = "Withdraw Money",
                    amountExpense = 3000,
                    percentageExpense = "34%"
                ), ExpensesModel(
                    imageExpense = R.drawable.ic_buy_airtime_pf,
                    titleExpense = "Buy Airtime",
                    amountExpense = 2000,
                    percentageExpense = "12%"
                )
            )
    }
}

data class MoreAboutExpense(
    val titleMoreExpense: String,
    val amountMoreExpense: Int,
    val timeMoreExpense: String
)