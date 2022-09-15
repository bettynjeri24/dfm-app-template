package com.ekenya.rnd.personalfinance.ui.adapters.multipleview


sealed class MoreExpenseRecyclerViewItem {

    data class HeaderMoreExpense(
        val durationMoreExpense: String,
    ) : MoreExpenseRecyclerViewItem()

    data class BodyMoreExpense(
        val titleMoreExpense: String,
        val durationMoreExpense: String,
        val amountMoreExpense: Int,
        val timeMoreExpense: String
    ) : MoreExpenseRecyclerViewItem()
}