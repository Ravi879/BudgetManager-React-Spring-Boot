export const getItemTotal = (items) => {
    const countItemTotal = (acc, cur) => acc + cur.amount;
    return items.reduce(countItemTotal, 0);
}

export const getTotalExpensePercentage = (totalIncome, totalExpense) => {
    if (totalIncome > 0) {
        return Math.round((totalExpense / totalIncome) * 100);
    } else {
        return 0;
    }
}
