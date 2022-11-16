Feature: SinglePlayer Tests

  Scenario Outline: SingleRoll Scores
    Given PlayerOne rolls <s1>,<s2>,<s3>,<s4>,<s5>,<s6>,<s7>,<s8> dice and Card <CARD> with face value <faceNum>
    And PlayerOne decides to end turn
    Then PlayerOne should have score <numScore>
    Examples:
      | s1 | s2 | s3 | s4 | s5 | s6 | s7 | s8 | CARD | faceNum | numScore |
      | "SKULL" | "SKULL" | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | 0 |





