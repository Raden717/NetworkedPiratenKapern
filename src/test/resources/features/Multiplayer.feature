Feature: Multiplayer Tests
  Everybody wants to know when it's Friday

  Scenario: Player wins after first set of turns
    Given PlayerOne rolls "SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "CAPTAIN"
    And PlayerOne decides to end turn
    And PlayerTwo rolls "SKULL","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD","SWORD" dice and Card "SKULL" with face value 1
    And PlayerTwo decides to end turn
    And PlayerThree rolls "SKULL","SKULL","SKULL","MONKEY","MONKEY","MONKEY","MONKEY","MONKEY" dice and Card "COIN"
    And PlayerThree decides to end turn
    When The set of turns end
    Then PlayerOne should have score 4000, PlayerTwo should have score 2000, PlayerThree should have score 0 and PlayerOne should have won true

