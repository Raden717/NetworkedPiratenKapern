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
      | "MONKEY" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "SWORD" | "DIAMOND" | "PARROT" | "GOLD" | 0 | 400 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "SWORD" | "COIN" | "COIN" | "CAPTAIN" | 0 | 1800 |
      | "MONKEY" | "MONKEY" | "MONKEY" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "DIAMOND" | "GOLD" | 0 | 1000 |
      | "MONKEY" | "MONKEY" | "PARROT" | "COIN" | "COIN" | "DIAMOND" | "DIAMOND" | "DIAMOND" | "MONKEY_BUSINESS" | 0 | 1200 |
      | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SKULL" | 2 | 0                     |
      | "SKULL" | "SKULL" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SWORD" | "SKULL" | 1 | 0                     |


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


  Scenario: Row90
    Given PlayerOne rolls "PARROT","PARROT","PARROT","SWORD","SWORD","DIAMOND","DIAMOND","COIN" dice and Card "TREASURE_CHEST" with face value 0
    And Player saves 3 dice in treasure chest at position 5, 6, 7
    And PlayerOne rolls again "PARROT","PARROT","PARROT","PARROT","PARROT","DIAMOND","DIAMOND","COIN" dice
    And Player clears chest and saves 5 dice in treasure chest, positions 0, 1, 2, 3, 4
    And PlayerOne rolls again "PARROT","PARROT","PARROT","PARROT","PARROT","SKULL","COIN","PARROT" dice
    And PlayerOne decides to end turn
    Then PlayerOne should have score 1100

  Scenario: Row94
    Given PlayerOne rolls "SKULL","SKULL","PARROT","PARROT","PARROT","COIN","COIN","COIN" dice and Card "TREASURE_CHEST" with face value 0
    And Player saves 3 dice in treasure chest at position 5, 6, 7
    And PlayerOne rolls again "SKULL","SKULL","DIAMOND","DIAMOND","COIN","COIN","COIN","COIN" dice
    And Player clears chest and saves 4 dice in treasure chest, positions 4, 5, 6, 7
    And PlayerOne rolls again "SKULL","SKULL","SKULL","COIN","COIN","COIN","COIN","COIN" dice
    And PlayerOne dies
    Then PlayerOne should have an updated score of 600 and should be dead


  Scenario: Row111
    Given PlayerOne rolls "SKULL","SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "SKULL" with face value 2
    And PlayerOne rolls again "SKULL","SKULL","SKULL","COIN","COIN","COIN","COIN","COIN" dice
    And PlayerOne decides to end turn
    And Scores are deducted by 5 due to island of skulls by player "One"
    Then PlayerOne should have score 0, PlayerTwo should have score 0, PlayerThree should have score 0

  Scenario: Row110
    Given PlayerOne rolls "SKULL","SKULL","SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY" dice and Card "CAPTAIN" with face value 0
    And PlayerOne rolls again "SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","COIN" dice
    And PlayerOne decides to end turn
    And Scores are deducted by 14 due to island of skulls by player "One"
    Then PlayerOne should have score 0, PlayerTwo should have score 0, PlayerThree should have score 0

  Scenario: Row109
    Given PlayerOne rolls "SKULL","SKULL","PARROT","PARROT","PARROT","MONKEY","MONKEY","MONKEY" dice and Card "SKULL" with face value 2
    And PlayerOne rolls again "SKULL","SKULL","SKULL","SKULL","SWORD","MONKEY","MONKEY","MONKEY" dice
    And PlayerOne rolls again "SKULL","SKULL","SKULL","SKULL","SWORD","SKULL","SKULL","SKULL" dice
    And PlayerOne decides to end turn
    And Scores are deducted by 9 due to island of skulls by player "One"
    Then PlayerOne should have score 0, PlayerTwo should have score 0, PlayerThree should have score 0


  Scenario: Row102
    Given PlayerOne rolls "SWORD","COIN","PARROT","PARROT","MONKEY","MONKEY","MONKEY","MONKEY" dice and Card "SEA_BATTLE" with face value 2
    And PlayerOne rolls again "SWORD","COIN","COIN","SWORD","MONKEY","MONKEY","MONKEY","MONKEY" dice
    And PlayerOne decides to end turn
    Then PlayerOne should have score 1200


#  FC: 2 sword sea battle, first  roll:  4 monkeys, 1 sword, 2 parrots and a coin
#  then reroll 2 parrots and get 2nd coin and 2nd sword
#  score is: 200 (coins) + 200 (monkeys) + 300 (swords of battle) + 500 (full chest) = 1200