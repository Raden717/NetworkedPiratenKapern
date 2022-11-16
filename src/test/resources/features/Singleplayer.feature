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
      | "MONKEY" | "MONKEY" | "MONKEY" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "COIN" | "MONKEY_BUSINESS" | 0 | 1100 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "PARROT" | "PARROT" | "SKULL" | "SKULL" | "SKULL" | "MONKEY_BUSINESS" | 0 | 0 |


  Scenario Outline: Roll and Reroll Once Scores
    Given PlayerOne rolls <s1>,<s2>,<s3>,<s4>,<s5>,<s6>,<s7>,<s8> dice and Card <CARD> with face value <faceNum>
    And PlayerOne rolls again <d1>,<d2>,<d3>,<d4>,<d5>,<d6>,<d7>,<d8> dice
    And PlayerOne decides to end turn
    Then PlayerOne should have score <numScore>
    Examples:
      | s1 | s2 | s3 | s4 | s5 | s6 | s7 | s8 | CARD | faceNum | d1 | d2 | d3 | d4 | d5 | d6 | d7 | d8 | numScore |
      | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "SKULL" | "SWORD" | 0 |
      | "SKULL" | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "SWORD" | 0 |
      | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "GOLD" | 0 | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "SWORD" | "MONKEY" | "SWORD" | "SWORD" | 300 |
      | "SKULL" | "COIN" | "COIN" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "COIN" | "COIN" | "COIN" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | 800 |
      | "SKULL" | "COIN" | "COIN" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "CAPTAIN" | 0 | "SKULL" | "COIN" | "COIN" | "COIN" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | 1200 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "GOLD" | 0 | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | "MONKEY" | 4600 |
      | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "DIAMOND" | 0 | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "DIAMOND" | "DIAMOND" | "SWORD" | "SWORD" | 400 |
      | "MONKEY" | "MONKEY" | "SKULL" | "SKULL" | "DIAMOND" | "PARROT" | "SWORD" | "SWORD" | "GOLD" | 0 | "DIAMOND" | "DIAMOND" | "SKULL" | "SKULL" | "DIAMOND" | "PARROT" | "SWORD" | "SWORD" | 500 |
      | "SKULL" | "COIN" | "COIN" | "MONKEY" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "COIN" | "COIN" | "MONKEY" | "PARROT" | "COIN" | "MONKEY" | "PARROT" | 600 |
      | "SKULL" | "COIN" | "COIN" | "MONKEY" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "DIAMOND" | 0 | "SKULL" | "COIN" | "COIN" | "MONKEY" | "PARROT" | "COIN" | "MONKEY" | "PARROT" | 500 |
      | "MONKEY" | "MONKEY" | "PARROT" | "PARROT" | "COIN" | "COIN" | "SWORD" | "SWORD" | "MONKEY_BUSINESS" | 0 | "MONKEY" | "MONKEY" | "PARROT" | "PARROT" | "COIN" | "COIN" | "MONKEY" | "PARROT" | 1700 |

  Scenario Outline: Roll and Rerolls Twice
    Given PlayerOne rolls <s1>,<s2>,<s3>,<s4>,<s5>,<s6>,<s7>,<s8> dice and Card <CARD> with face value <faceNum>
    And PlayerOne rolls again <d1>,<d2>,<d3>,<d4>,<d5>,<d6>,<d7>,<d8> dice
    And PlayerOne rolls again <e1>,<e2>,<e3>,<e4>,<e5>,<e6>,<e7>,<e8> dice
    And PlayerOne decides to end turn
    Then PlayerOne should have score <numScore>
    Examples:
      | s1 | s2 | s3 | s4 | s5 | s6 | s7 | s8 | CARD | faceNum | d1 | d2 | d3 | d4 | d5 | d6 | d7 | d8 | e1 | e2 | e3 | e4 | e5 | e6 | e7 | e8 | numScore |
      | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "MONKEY" | "MONKEY" | "SKULL" | "PARROT" | "PARROT" | "PARROT" | "PARROT" | "SKULL" | "SKULL" | "MONKEY" | 0 |
      | "SKULL" | "PARROT" | "PARROT" | "COIN" | "COIN" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "COIN" | "COIN" | "COIN" | "COIN" | "SWORD" | "SWORD" | "SWORD" | "SKULL" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | "COIN" | 4800 |
      | "SKULL" | "PARROT" | "PARROT" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "SWORD" | "GOLD" | 0 | "SKULL" | "PARROT" | "PARROT" | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SKULL" | "SWORD" | "MONKEY" | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | 600 |



