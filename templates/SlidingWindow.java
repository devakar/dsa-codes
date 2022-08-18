def sliding_window(nums):
    left, right = 0, 0        # Our window bounds
    count_of_zeroes = 0       # Track how many 0’s are in the window
    global_max = 0            # Track the maximum, overall
    # Iterate over elements in our input
    while right < len(nums):
        # Expand the window 
        if nums[right] == 0:        
            count_of_zeroes += 1
        # Meet the condition to stop expansion
        while count_of_zeroes == 2:
        # Process the current window
            global_max = max(global_max, right - left)
        # Contract the window
            if nums[left] == 0:
                count_of_zeroes -= 1
            left += 1
        right += 1
    if count_of_zeroes < 2:
        global_max = max(global_max, right-left)
    return global_max



    1. Set left and right
    2. Do calculations required for criteria
    3. If it matches criteria return or Set
    4. Expand right based on criteria
    5. Reduce left if can't expand based on criteria