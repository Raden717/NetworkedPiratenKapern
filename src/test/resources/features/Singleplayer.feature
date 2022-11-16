Feature: SinglePlayer Tests

  Scenario Outline: SingleRoll Scores
    Given PlayerOne rolls <s1>,<s2>,<s3>,<s4>,<s5>,<s6>,<s7>,<s8> dice and Card <CARD> with face value <faceNum>
    And PlayerOne decides to end turn
    Then PlayerOne should have score <numScore>
    Examples:
      | s1 | s2 | s3 | s4 | s5 | s6 | s7 | s8 | CARD | faceNum | numScore |
      | "SKULL" | "SKULL" | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | 0 |
      | "SKULL" | "SKULL" | "MONKEY" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | 300 |
      | "DIAMOND" | "DIAMOND" | "DIAMOND" | "SKULL" | "SKULL" | "MONKEY" | "SWORD" | "PARROT" | "GOLD" | 0 | 500 |
      | "COIN" | "COIN" | "COIN" | "COIN" | "SKULL" | "SKULL" | "SWORD" | "SWORD" | "DIAMOND" | 0 | 700 |
      | "SWORD" | "SWORD" | "SWORD" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "GOLD" | 0 | 400 |
      | "MONKEY" | "MONKEY" | "PARROT" | "PARROT" | "DIAMOND" | "DIAMOND" | "COIN" | "COIN" | "CAPTAIN" | 0 | 800 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "GOLD" | 0 | 1100 |
      | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "GOLD" | 0 | 2100 |
      | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "GOLD" | 0 | 5400 |
      | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "DIAMOND" | 0 | 5400 |
      | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "CAPTAIN" | 0 | 9000 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "COIN" | "COIN" | "SKULL" | "SKULL" | "GOLD" | 0 | 600 |

  Scenario Outline: Roll and Reroll Once Scores
    Given PlayerOne rolls <s1>,<s2>,<s3>,<s4>,<s5>,<s6>,<s7>,<s8> dice and Card <CARD> with face value <faceNum>
    And PlayerOne rolls again <d1>,<d2>,<d3>,<d4>,<d5>,<d6>,<d7>,<d8> dice
    And PlayerOne decides to end turn
    Then PlayerOne should have score <numScore>
    Examples:
      | s1 | s2 | s3 | s4 | s5 | s6 | s7 | s8 | CARD | faceNum | d1 | d2 | d3 | d4 | d5 | d6 | d7 | d8 | numScore |
      | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "SKULL" | "SWORD" | 0 |


