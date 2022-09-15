package com.ekenya.rnd.personalfinance.data.repos


import com.ekenya.rnd.personalfinance.ui.adapters.multipleview.MoreExpenseRecyclerViewItem

class ExpensesRepository {

    fun getMoreAboutExpense() = arrayListOf(
        MoreExpenseRecyclerViewItem.BodyMoreExpense(
            titleMoreExpense = "Send to Mpesa",
            durationMoreExpense = "Today",
            amountMoreExpense = 2000,
            timeMoreExpense = "12:35pm"
        ),
        MoreExpenseRecyclerViewItem.BodyMoreExpense(
            titleMoreExpense = "Bank Transfer",
            durationMoreExpense = "Older",
            amountMoreExpense = 2000,
            timeMoreExpense = "12:35pm"
        ),
        MoreExpenseRecyclerViewItem.BodyMoreExpense(
            titleMoreExpense = "Funds Transfer Expense",
            durationMoreExpense = "Today",
            amountMoreExpense = 2000,
            timeMoreExpense = "12:35pm"
        ),
        MoreExpenseRecyclerViewItem.BodyMoreExpense(
            titleMoreExpense = "Funds Transfer Expense",
            durationMoreExpense = "Today",
            amountMoreExpense = 4000,
            timeMoreExpense = "12:35pm"
        ),
        MoreExpenseRecyclerViewItem.BodyMoreExpense(
            titleMoreExpense = "Pesalink transfer",
            durationMoreExpense = "Older",
            amountMoreExpense = 2000,
            timeMoreExpense = "12:35pm"
        ),

        )


}