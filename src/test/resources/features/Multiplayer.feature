Feature: Multiplayer Tests
  Everybody wants to know when it's Friday

  Scenario: Row132
    Given PlayerOne rolls "SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "CAPTAIN"
    And PlayerOne decides to end turn
    And PlayerTwo rolls "SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "SKULL" with face value 1
    And PlayerTwo decides to end turn
    And PlayerThree rolls "SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY" dice and Card "GOLD"
    And PlayerThree decides to end turn
    When The set of turns end
    Then PlayerOne should have score 4000, PlayerTwo should have score 2000, PlayerThree should have score 0 and PlayerOne should have won true

  Scenario: Row142
    Given PlayerOne rolls "SKULL","SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "CAPTAIN"
    And PlayerOne decides to end turn
    And PlayerTwo rolls "SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "CAPTAIN" with face value 0
    And PlayerTwo decides to end turn
    And PlayerThree rolls "SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY" dice and Card "SKULL" with a face value 2
    And PlayerThree decides to end turn
    And PlayerOne rolls "SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "CAPTAIN" with a face value 0
    And PlayerOne decides to end turn
    When The set of turns end
    Then PlayerOne should have score 9000, PlayerTwo should have score 4000, PlayerThree should have score 0 and PlayerOne should have won true


  Scenario: Row150
    Given PlayerOne rolls "SKULL","SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "GOLD"
    And PlayerOne decides to end turn
    And PlayerTwo rolls "SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","COIN" dice and Card "SORCERESS" with face value 0
    And PlayerTwo rerolls to have "SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","PARROT","COIN"
    And PlayerTwo rerolls to have "SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL","SKULL"
    And Scores are deducted by 8 due to island of skulls by player "two"
    When checking scores
    Then PlayerOne should have score 300, PlayerTwo should have score 0, PlayerThree should have score 0


