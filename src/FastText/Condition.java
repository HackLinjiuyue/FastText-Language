package FastText;

import Exceptions.SymbolSyntaxError;

public class Condition {
	static boolean ifCondition(String condition) {

		// delete space
		// System.out.println(condition);
		condition = condition.replaceAll(" ", "");
		String symbol = condition.replaceAll("[a-zA-Z]", "").replaceAll("\\+", "").replaceAll("\\-", "")
				.replaceAll("\\*", "").replaceAll("\\/", "");
		// System.out.println(symbol);
		switch (symbol) {
		case Grammar.FT_equal:
			String[] PARTS = condition.split(Grammar.FT_equal);

			// System.out.println(PARTS[0]);
			if (Expression.isNotExpression(PARTS[0]) && Expression.isNotExpression(PARTS[1])) {

				return LocalValues.NumValues.containsKey(PARTS[0]) && LocalValues.NumValues.containsKey(PARTS[1])
						? LocalValues.NumValues.get(PARTS[0]).equals(LocalValues.NumValues.get(PARTS[1]))
						: LocalValues.values.get(PARTS[0]).equals(LocalValues.values.get(PARTS[1]));

			} else {
				if (!Expression.isNotExpression(PARTS[0]) && Expression.isNotExpression(PARTS[1])) {
					return !Expression.isNotExpression(PARTS[0]) && Expression.isNotExpression(PARTS[1])
							? ((Integer) Expression.solveExpression(PARTS[0]))
									.equals(LocalValues.NumValues.get(PARTS[1]))
							: ((Integer) Expression.solveExpression(PARTS[1]))
									.equals(LocalValues.NumValues.get(PARTS[0]));

				} else if (!Expression.isNotExpression(PARTS[0]) && !Expression.isNotExpression(PARTS[1])) {
					return !Expression.isNotExpression(PARTS[0]) && !Expression.isNotExpression(PARTS[1])
							? ((Integer) Expression.solveExpression(PARTS[0]))
									.equals(Expression.solveExpression(PARTS[1]))
							: false;
				} else {
					return Expression.isNotExpression(PARTS[0]) && !Expression.isNotExpression(PARTS[1])
							? ((Integer) Expression.solveExpression(PARTS[1]))
									.equals(LocalValues.NumValues.get(PARTS[0]))
							: false;
				}

			}

		case Grammar.FT_unequal:
			String[] PARTS_ = condition.split(Grammar.FT_unequal);

			if (Expression.isNotExpression(PARTS_[0]) && Expression.isNotExpression(PARTS_[1])) {
				return LocalValues.NumValues.containsKey(PARTS_[0]) && LocalValues.NumValues.containsKey(PARTS_[1])
						? !LocalValues.NumValues.get(PARTS_[0]).equals(LocalValues.NumValues.get(PARTS_[1]))
						: !LocalValues.values.get(PARTS_[0]).equals(LocalValues.values.get(PARTS_[1]));
			} else {
				if (!Expression.isNotExpression(PARTS_[0]) && Expression.isNotExpression(PARTS_[1])) {
					return !Expression.isNotExpression(PARTS_[0]) && Expression.isNotExpression(PARTS_[1])
							? ((Integer) Expression.solveExpression(PARTS_[0]))
									.equals(LocalValues.NumValues.get(PARTS_[1]))
							: ((Integer) Expression.solveExpression(PARTS_[1]))
									.equals(LocalValues.NumValues.get(PARTS_[0]));

				} else if (!Expression.isNotExpression(PARTS_[0]) && !Expression.isNotExpression(PARTS_[1])) {
					return !Expression.isNotExpression(PARTS_[0]) && !Expression.isNotExpression(PARTS_[1])
							? !((Integer) Expression.solveExpression(PARTS_[0]))
									.equals(Expression.solveExpression(PARTS_[1]))
							: false;
				} else {
					return Expression.isNotExpression(PARTS_[0]) && !Expression.isNotExpression(PARTS_[1])
							? ((Integer) Expression.solveExpression(PARTS_[1]))
									.equals(LocalValues.NumValues.get(PARTS_[0]))
							: false;
				}

			}

		case Grammar.FT_elager:
			String[] _PARTS = condition.split(Grammar.FT_elager);

			if (Expression.isNotExpression(_PARTS[0]) && Expression.isNotExpression(_PARTS[1])) {
				return LocalValues.NumValues.containsKey(_PARTS[0]) && LocalValues.NumValues.containsKey(_PARTS[1])
						? LocalValues.NumValues.get(_PARTS[0]) >= LocalValues.NumValues.get(_PARTS[1])
						: false;
			} else {
				if (!Expression.isNotExpression(_PARTS[0]) && Expression.isNotExpression(_PARTS[1])) {
					return !Expression.isNotExpression(_PARTS[0]) && Expression.isNotExpression(_PARTS[1])
							? ((Integer) Expression.solveExpression(_PARTS[0])) >= (LocalValues.NumValues
									.get(_PARTS[1]))
							: ((Integer) Expression.solveExpression(_PARTS[1])) >= (LocalValues.NumValues
									.get(_PARTS[0]));

				} else if (!Expression.isNotExpression(_PARTS[0]) && !Expression.isNotExpression(_PARTS[1])) {
					return !Expression.isNotExpression(_PARTS[0]) && !Expression.isNotExpression(_PARTS[1])
							? ((Integer) Expression.solveExpression(_PARTS[0])) >= (Expression
									.solveExpression(_PARTS[1]))
							: false;
				} else {
					return Expression.isNotExpression(_PARTS[0]) && !Expression.isNotExpression(_PARTS[1])
							? ((Integer) Expression.solveExpression(_PARTS[1]))
									.equals(LocalValues.NumValues.get(_PARTS[0]))
							: false;
				}

			}

		case Grammar.FT_esmaller:
			String[] _PARTS_ = condition.split(Grammar.FT_esmaller);

			if (Expression.isNotExpression(_PARTS_[0]) && Expression.isNotExpression(_PARTS_[1])) {
				return LocalValues.NumValues.containsKey(_PARTS_[0]) && LocalValues.NumValues.containsKey(_PARTS_[1])
						? LocalValues.NumValues.get(_PARTS_[0]) <= LocalValues.NumValues.get(_PARTS_[1])
						: false;
			} else {
				if (!Expression.isNotExpression(_PARTS_[0]) && Expression.isNotExpression(_PARTS_[1])) {
					return !Expression.isNotExpression(_PARTS_[0]) && Expression.isNotExpression(_PARTS_[1])
							? ((Integer) Expression.solveExpression(_PARTS_[0])) <= (LocalValues.NumValues
									.get(_PARTS_[1]))
							: ((Integer) Expression.solveExpression(_PARTS_[1])) <= (LocalValues.NumValues
									.get(_PARTS_[0]));

				} else if (!Expression.isNotExpression(_PARTS_[0]) && !Expression.isNotExpression(_PARTS_[1])) {
					return !Expression.isNotExpression(_PARTS_[0]) && !Expression.isNotExpression(_PARTS_[1])
							? ((Integer) Expression.solveExpression(_PARTS_[0])) <= (Expression
									.solveExpression(_PARTS_[1]))
							: false;
				} else {
					return Expression.isNotExpression(_PARTS_[0]) && !Expression.isNotExpression(_PARTS_[1])
							? ((Integer) Expression.solveExpression(_PARTS_[1]))
									.equals(LocalValues.NumValues.get(_PARTS_[0]))
							: false;
				}
			}

		case Grammar.FT_smaller:
			String[] _PARTS__ = condition.split(Grammar.FT_smaller);

			if (Expression.isNotExpression(_PARTS__[0]) && Expression.isNotExpression(_PARTS__[1])) {
				return LocalValues.NumValues.containsKey(_PARTS__[0]) && LocalValues.NumValues.containsKey(_PARTS__[1])
						? LocalValues.NumValues.get(_PARTS__[0]) < LocalValues.NumValues.get(_PARTS__[1])
						: false;
			} else {
				if (!Expression.isNotExpression(_PARTS__[0]) && Expression.isNotExpression(_PARTS__[1])) {
					return !Expression.isNotExpression(_PARTS__[0]) && Expression.isNotExpression(_PARTS__[1])
							? ((Integer) Expression.solveExpression(_PARTS__[0])) < (LocalValues.NumValues
									.get(_PARTS__[1]))
							: ((Integer) Expression.solveExpression(_PARTS__[1])) < (LocalValues.NumValues
									.get(_PARTS__[0]));

				} else if (!Expression.isNotExpression(_PARTS__[0]) && !Expression.isNotExpression(_PARTS__[1])) {
					return !Expression.isNotExpression(_PARTS__[0]) && !Expression.isNotExpression(_PARTS__[1])
							? ((Integer) Expression.solveExpression(_PARTS__[0])) < (Expression
									.solveExpression(_PARTS__[1]))
							: false;
				} else {
					return Expression.isNotExpression(_PARTS__[0]) && !Expression.isNotExpression(_PARTS__[1])
							? ((Integer) Expression.solveExpression(_PARTS__[1]))
									.equals(LocalValues.NumValues.get(_PARTS__[0]))
							: false;
				}
			}

		case Grammar.FT_larger:
			String[] __PARTS = condition.split(Grammar.FT_larger);

			if (Expression.isNotExpression(__PARTS[0]) && Expression.isNotExpression(__PARTS[1])) {
				return LocalValues.NumValues.containsKey(__PARTS[0]) && LocalValues.NumValues.containsKey(__PARTS[1])
						? LocalValues.NumValues.get(__PARTS[0]) > LocalValues.NumValues.get(__PARTS[1])
						: false;
			} else {
				if (!Expression.isNotExpression(__PARTS[0]) && Expression.isNotExpression(__PARTS[1])) {
					return !Expression.isNotExpression(__PARTS[0]) && Expression.isNotExpression(__PARTS[1])
							? ((Integer) Expression.solveExpression(__PARTS[0])) > (LocalValues.NumValues
									.get(__PARTS[1]))
							: ((Integer) Expression.solveExpression(__PARTS[1])) > (LocalValues.NumValues
									.get(__PARTS[0]));

				} else if (!Expression.isNotExpression(__PARTS[0]) && !Expression.isNotExpression(__PARTS[1])) {
					return !Expression.isNotExpression(__PARTS[0]) && !Expression.isNotExpression(__PARTS[1])
							? ((Integer) Expression.solveExpression(__PARTS[0])) > (Expression
									.solveExpression(__PARTS[1]))
							: false;
				} else {
					return Expression.isNotExpression(__PARTS[0]) && !Expression.isNotExpression(__PARTS[1])
							? ((Integer) Expression.solveExpression(__PARTS[1]))
									.equals(LocalValues.NumValues.get(__PARTS[0]))
							: false;
				}
			}

		default:
			throw new SymbolSyntaxError(symbol);
		}
	}
}
